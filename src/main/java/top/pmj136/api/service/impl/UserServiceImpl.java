package top.pmj136.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import top.pmj136.api.component.CookieHelper;
import top.pmj136.api.component.MailHelper;
import top.pmj136.api.component.RedisHelper;
import top.pmj136.api.component.UploadHelper;
import top.pmj136.api.entity.User;
import top.pmj136.api.mapper.MsgNoticeMapper;
import top.pmj136.api.mapper.MsgRemarkMapper;
import top.pmj136.api.mapper.MsgStarMapper;
import top.pmj136.api.mapper.UserMapper;
import top.pmj136.api.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.pmj136.api.util.*;
import top.pmj136.api.wordfilter.WordFilter;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 彭明久
 * @since 2020-11-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private RedisHelper redisHelper;

    @Resource
    private UploadHelper uploadHelper;

    @Resource
    private CookieHelper cookieHelper;

    @Resource
    private MailHelper mailHelper;

    @Value("${api.token.expire}")
    private long expire;

    @Value("${api.upload.default-avatar}")
    private String defaultAvatar;

    @Resource
    private MsgNoticeMapper msgNoticeMapper;

    @Resource
    private MsgRemarkMapper msgRemarkMapper;

    @Resource
    private MsgStarMapper msgStarMapper;

    @Resource
    private WordFilter wordFilter;

    public Result login(Map<String, Object> req) {
        String email = (String) req.get("email");
        String password = (String) req.get("password");
        String code = (String) req.get("code");
        String client = (String) req.get("client");
        User user;
        if (code == null) {//账密登录
            user = findUser("email", email);
            if (user == null || !password.equals(user.getPassword())) return Result.reject("邮箱或密码有误");
            return checkUser(user, client);
        }
        String nick = (String) req.get("nick");
        String sCode = (String) redisHelper.get(email);
        if (sCode == null) return Result.reject("验证码已过期");
        if (!code.equals(sCode)) return Result.reject("验证码有误");
        if (wordFilter.include(nick)) return Result.reject("昵称不得含有敏感字");
        redisHelper.del(email);
        user = findUser("email", email);
        if (user == null) {
            user = new User();
            user.setEmail(email);
            user.setNick(nick);
            user.setPassword(password);
            Integer user_id = addUser(user);
            user.setId(user_id);
            return handleLogin(user, client);
        } else
            return checkUser(user, client);
    }

    public Result logout(String token, String client) {
        Object cacheData = redisHelper.get(token);
        Map userData = JSONObject.parseObject(JSONObject.toJSONString(cacheData), Map.class);
        if (userData.get(getOppositeClient(client)) == null) {
            redisHelper.del(token);
            return Result.resolve(true);
        }
        userData.remove(client);
        redisHelper.set(token, userData, redisHelper.getExpire(token));
        return Result.resolve(true);
    }

    public Result auth(Integer user_id) {
        if (user_id == null) return Result.build(200, "未登录", null);
        return Result.resolve(findUser("id", user_id));
    }

    public Result getInfo(Integer find_id, Integer user_id) {
        return Result.resolve(baseMapper.getById(find_id, user_id));
    }

    public Result updateInfo(User user) {
        if (user.getNick() != null && wordFilter.include(user.getNick()))
            return Result.reject("昵称不得含有敏感字");
        baseMapper.updateById(user);
        return Result.resolve("更新成功", true);
    }

    public Result updatePwd(User user, String oldToken) {
        redisHelper.del(oldToken);
        baseMapper.updateById(user);
        return Result.resolve("密码修改成功，请重新登录");
    }

    public Result uploadAvatar(Integer user_id, MultipartFile file) {
        String url = null;
        Map<String, Object> objectMap = uploadHelper.push("avatar", file);
        if ((boolean) objectMap.get("flag")) url = (String) objectMap.get("url");
        if (url == null) return Result.reject("头像上传失败");
        User user = new User();
        user.setId(user_id);
        user.setAvatarUrl(url);
        baseMapper.updateById(user);
        return Result.resolve("更新成功", url);
    }

    public Result sign(Integer user_id) {
        Map<String, Object> continuousSign = baseMapper.isContinuousSign(user_id);
        Object gap = continuousSign.get("gap_days");
        int gap_days = gap == null ? 0 : Integer.parseInt(gap.toString());//间隔的天数
        int signed_days = Integer.parseInt(continuousSign.get("signed_days").toString());//已签到天数
        int final_days = signed_days + 1;
        int add_integral = calcIntegral(gap_days, final_days);//可新增积分
        String sql = "last_sign_date='" + LocalDate.now() + "'" + ",integral=integral+" + add_integral;
        if (gap_days == 1)
            sql += ",signed_days=signed_days+1";
        else
            sql += ",signed_days=1";

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql(sql).eq("id", user_id);
        update(updateWrapper);

        Map<String, Integer> resp = new HashMap<>();
        resp.put("days", gap_days == 1 ? final_days : 1);
        resp.put("add_integral", add_integral);
        return Result.resolve("签到成功", resp);
    }

    public Result verifyEmail(String email) {
        User user = findUser("state", "email", email);
        if (user != null) {
            if (user.getState() != 1) return Result.reject("用户账户异常");
            return Result.resolve(true);
        }
        return Result.resolve(false);
    }

    public Result postCode(String email) {
        String code = generateCode();
        mailHelper.send("久久社区", "您的登录验证码是 " + code + ", 2分钟内有效", email);
        redisHelper.set(email, code, 120);
        return Result.resolve("验证码发送成功");
    }

    public Result bindEmail(Map<String, Object> req) {
        String email = (String) req.get("email");
        String code = (String) req.get("code");
        String sCode = (String) redisHelper.get(email);
        if (sCode == null) return Result.reject("验证码已过期");
        if (!code.equals(sCode)) return Result.reject("验证码有误");
        redisHelper.del(email);
        String password = (String) req.get("password");
        Integer user_id = (Integer) req.get("user_id");
        User user = new User();
        user.setId(user_id);
        user.setEmail(email);
        user.setPassword(password);
        baseMapper.updateById(user);
        return Result.resolve("绑定成功");
    }

    public Result getDynamics(Integer page, Integer size, Integer user_id, Integer find_id, Integer type) {
        return Result.resolve(baseMapper.getDynamicsList(new Page<>(page, size), user_id, find_id, type));
    }

    public Result unbind(Map<String, Object> req) {
        User user = findUser("id,email", "id", req.get("user_id"), 2);
        if (user.getEmail() == null) return Result.reject("解绑失败，请先绑定邮箱");
        return Result.resolve("解绑成功", baseMapper.unbind(req));
    }

    public Result getUserList(Integer page, Integer size, String keyword) {
        return Result.resolve(baseMapper.getUserList(new Page<>(page, size), keyword));
    }

    public Result freeze(Integer user_id) {
        User u = findUser("token,role", "id", user_id);
        if (u.getRole() == 3) return Result.reject("没有权限");
        String token = u.getToken();
        User user = new User();
        user.setId(user_id);
        user.setState(0);
        updateInfo(user);
        if (redisHelper.hasKey(token)) redisHelper.del(token);
        return Result.resolve("冻结成功");
    }

    public User findUser(String column, Object val) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(column, val);
        return getOne(queryWrapper);
    }

    public User findUser(String pros, String column, Object val) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(pros).eq(column, val);
        return getOne(queryWrapper);
    }

    public User findUser(String pros, String column, Object val, Integer role) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(pros).eq(column, val).eq("role", role);
        return getOne(queryWrapper);
    }

    public Integer addUser(User user) {
        if (user.getAvatarUrl() == null) user.setAvatarUrl(defaultAvatar);
        user.setCreateAt(LocalDate.now());
        baseMapper.addUser(user);
        return user.getId();
    }

    public Result countMsg(Integer user_id) {
        if (user_id == null) return Result.build(200, "未登录", null);
        Map<String, Object> req = new HashMap<>();
        req.put("target_id", user_id);
        req.put("range", 1);

        Map<String, Object> resp = new HashMap<>();
        resp.put("notice_count", msgNoticeMapper.getNewMsgTotal(req));
        resp.put("remark_count", msgRemarkMapper.getNewMsgTotal(req));
        resp.put("star_count", msgStarMapper.getNewMsgTotal(req));
        return Result.resolve(resp);
    }

    public Map<String, Object> getGenderInfo() {
        int man_count = baseMapper.countGender(1);
        int woman_count = baseMapper.countGender(2);
        int un = baseMapper.countGender(0);
        Map<String, Object> resp = new HashMap<>();
        resp.put("man_count", man_count);
        resp.put("woman_count", woman_count);
        resp.put("un", un);
        return resp;
    }

    public List<Map<String, Object>> getGrowth() {
        return baseMapper.getGrowth();
    }

    public Map<String, Object> getSignInfo(Integer user_id) {
        if (user_id == null) return null;
        return baseMapper.getSignInfo(user_id);
    }

    public Result handleLogin(User user, String client) {
        boolean isNeedUpdate = false;
        String loginToken = user.getToken();

        String token;
        if (loginToken == null) {
            token = generateToken(user);
            isNeedUpdate = true;
        } else {
            Long expire1 = redisHelper.getExpire(loginToken);
            if (expire1 == -1 || (expire1 > 0 && expire1 > expire / 2))
                token = loginToken;
            else {
                token = generateToken(user);
                if (expire1 != -2) redisHelper.del(loginToken);
                isNeedUpdate = true;
            }
        }
        if (isNeedUpdate) {
            User user1 = new User();
            user1.setId(user.getId());
            user1.setToken(token);
            baseMapper.updateById(user1);
        }
        Object cacheData = redisHelper.get(token);
        if (cacheData == null) {
            Map<String, Object> redisData = new HashMap<>();
            redisData.put("user_id", user.getId());
            redisData.put(client, true);
            redisHelper.set(token, redisData, expire);
        } else {
            Map userData = JSONObject.parseObject(JSONObject.toJSONString(cacheData), Map.class);
            userData.put(client, true);
            redisHelper.set(token, userData, expire);
        }

        cookieHelper.set("token", token);
        return Result.resolve(user);
    }


    @Async
    public void alterNotice(Integer user_id, String type) {
        String sql = "notice_count=notice_count+1";
        if (type.equals("decrease")) sql = "notice_count=notice_count-1";
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql(sql).eq("id", user_id);
        update(updateWrapper);
    }

    @Async
    public void alterFans(Integer user_id, String type) {
        String sql = "fans_count = fans_count+1";
        if (type.equals("decrease")) sql = "fans_count = fans_count-1";
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql(sql).eq("id", user_id);
        update(updateWrapper);
    }

    @Async
    public void increaseIntegral(Integer user_id, Integer num) {
        String sql = "integral=integral+" + num;
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.setSql(sql).eq("id", user_id);
        update(updateWrapper);
    }

    /*判断用户是否可以登录*/
    private Result checkUser(User user, String client) {
        if (user.getState() != 1) return Result.reject("用户账户异常");
        int userRole = user.getRole();
        if (userRole == 1 && "admin".equals(client)) return Result.reject("用户暂无权限");
        return handleLogin(user, client);
    }

    /*获取客户端*/
    private String getOppositeClient(String client) {
        if ("admin".equals(client)) return "user";
        return "admin";
    }

    /*计算签到积分*/
    private Integer calcIntegral(int gap, int days) {
        int i = 2;
        if (gap == 1) {
            if (days >= 5 && days < 15) i = 3;
            if (days >= 15 && days < 30) i = 4;
            if (days >= 30) i = 5;
        }
        return i;
    }

    /*生成token*/
    private String generateToken(User user) {
        return DigestUtils.md5DigestAsHex((user.getId() + System.currentTimeMillis() + "").getBytes());
    }

    /*生成验证码*/
    private String generateCode() {
        Random random = new Random();
        return random.nextInt(9000) + 1000 + "";
    }
}

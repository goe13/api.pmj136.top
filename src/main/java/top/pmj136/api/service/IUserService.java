package top.pmj136.api.service;

import org.springframework.web.multipart.MultipartFile;
import top.pmj136.api.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import top.pmj136.api.util.Result;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 彭明久
 * @since 2020-11-26
 */
public interface IUserService extends IService<User> {
    /**
     * 用户登录
     *
     * @param req Map
     * @return Result
     */
    Result login(Map<String, Object> req);

    /**
     * 注销登录
     *
     * @param token  用户登录凭证
     * @param client 用户设备
     * @return Result
     */
    Result logout(String token, String client);

    /**
     * 用户登录检测
     *
     * @param user_id 当前请求请求后台用户id
     * @return Result
     */
    Result auth(Integer user_id);

    /**
     * 获取用户信息
     *
     * @param find_id 需获取的用户id
     * @param user_id 当前请求请求后台用户id
     * @return Result
     */
    Result getInfo(Integer find_id, Integer user_id);

    /**
     * 更新用户信息
     *
     * @param entity User
     * @return Result
     */
    Result updateInfo(User entity);

    /**
     * 修改密码
     *
     * @param entity   User
     * @param oldToken 用户token
     * @return Result
     */
    Result updatePwd(User entity, String oldToken);


    /**
     * 用户上传头像
     *
     * @param user_id 用户id
     * @param file    头像文件
     * @return Result
     */
    Result uploadAvatar(Integer user_id, MultipartFile file);

    /**
     * 用户签到
     *
     * @param user_id 用户id
     * @return Result
     */
    Result sign(Integer user_id);

    /**
     * 邮箱验证
     *
     * @param email 邮箱
     * @return Result
     */
    Result verifyEmail(String email);

    /**
     * 发送验证码
     *
     * @param email 邮箱
     * @return Result
     */
    Result postCode(String email);

    /**
     * 绑定邮箱
     *
     * @param req Map
     * @return Result
     */
    Result bindEmail(Map<String, Object> req);

    /**
     * 获取用户动态列表
     *
     * @param page    pageIndex
     * @param size    size
     * @param user_id 用户id
     * @param find_id 浏览用户id
     * @param type    类型
     * @return Result
     */
    Result getDynamics(Integer page, Integer size, Integer user_id, Integer find_id, Integer type);

    /**
     * 第三方解绑
     *
     * @param req Map
     * @return Result
     */
    Result unbind(Map<String, Object> req);

    /**
     * 管理员分页获取用户列表
     *
     * @param page    pageIndex
     * @param size    size
     * @param keyword 关键字
     * @return Result
     */
    Result getUserList(Integer page, Integer size, String keyword);

    /**
     * 冻结用户
     *
     * @param user_id 用户id
     * @return Result
     */
    Result freeze(Integer user_id);

    /**
     * 查找用户数据(查询所有)
     *
     * @param column 数据库列
     * @param val    条件值
     * @return Result
     */
    User findUser(String column, Object val);

    /**
     * 查找用户数据(查找pros)
     *
     * @param pros   查询字段
     * @param column 数据库列
     * @param val    条件值
     * @return Result
     */
    User findUser(String pros, String column, Object val);

    /**
     * 查找用户数据(查找pros，区分角色)
     *
     * @param pros   查询字段
     * @param column 数据库列
     * @param val    条件值
     * @param role   角色
     * @return Result
     */
    User findUser(String pros, String column, Object val, Integer role);

    /**
     * 添加用户
     *
     * @param entity User
     * @return Result
     */
    Integer addUser(User entity);

    /**
     * 消息计数
     *
     * @param user_id 当前登录用户id
     * @return Result
     */
    Result countMsg(Integer user_id);

    /**
     * 获取性别信息
     *
     * @return Result
     */
    Map<String, Object> getGenderInfo();

    /**
     * 获取用户增长信息
     *
     * @return Result
     */
    List<Map<String, Object>> getGrowth();

    /**
     * 获取用户签到信息
     *
     * @param user_id 用户id
     * @return Result
     */
    Map<String, Object> getSignInfo(Integer user_id);

    void alterNotice(Integer user_id, String type);

    void alterFans(Integer user_id, String type);

    void increaseIntegral(Integer user_id, Integer num);
}

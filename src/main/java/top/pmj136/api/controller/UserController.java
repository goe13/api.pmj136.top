package top.pmj136.api.controller;


import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import top.pmj136.api.entity.Notice;
import top.pmj136.api.entity.User;
import top.pmj136.api.service.impl.NoticeServiceImpl;
import top.pmj136.api.service.impl.UserServiceImpl;
import top.pmj136.api.util.Result;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 彭明久
 * @since 2020-11-26
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserServiceImpl userService;

    @Resource
    private NoticeServiceImpl noticeService;

    /*用户登录*/
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, Object> req,
                        @ModelAttribute("client") String client) {
        req.put("client", client);
        return userService.login(req);
    }

    /*退出登录*/
    @PostMapping("/logout")
    public Result logout(@CookieValue(name = "token") String token,
                         @ModelAttribute("client") String client) {
        return userService.logout(token, client);
    }

    /*当前用户信息*/
    @GetMapping("/auth")
    public Result auth(@ModelAttribute("user_id") Integer user_id) {
        return userService.auth(user_id);
    }

    /*获取用户信息*/
    @GetMapping("/get")
    public Result getInfo(@ModelAttribute("user_id") Integer user_id,
                          @RequestParam("id") Integer find_id) {
        return userService.getInfo(find_id, user_id);
    }

    /*更新用户信息*/
    @PostMapping("/info/update")
    public Result updateInfo(@ModelAttribute("user_id") Integer user_id,
                             @RequestBody User user) {
        user.setId(user_id);
        return userService.updateInfo(user);
    }


    /*密码修改*/
    @PostMapping("/pwd/update")
    public Result updatePwd(@ModelAttribute("user_id") Integer user_id,
                            @CookieValue(value = "token") String oldToken,
                            @RequestBody User user) {
        user.setId(user_id);
        return userService.updatePwd(user, oldToken);
    }

    /*用户上传头像*/
    @PostMapping("/avatar/upload")
    public Result uploadAvatar(@ModelAttribute("user_id") Integer user_id,
                               @RequestParam("img") MultipartFile img) {
        return userService.uploadAvatar(user_id, img);
    }

    /*用户签到*/
    @PostMapping("/sign")
    public Result sign(@ModelAttribute("user_id") Integer user_id) {
        return userService.sign(user_id);
    }

    /*判断邮箱是否被注册*/
    @PostMapping("/email/check")
    public Result verifyEmail(@RequestBody Map<String, Object> req) {
        return userService.verifyEmail((String) req.get("email"));
    }

    /*发送验证码*/
    @PostMapping("/code/post")
    public Result postCode(@RequestBody Map<String, Object> req) {
        return userService.postCode((String) req.get("email"));
    }

    /*绑定邮箱*/
    @PostMapping("/email/bind")
    public Result bindEmail(@ModelAttribute("user_id") Integer user_id,
                            @RequestBody Map<String, Object> req) {
        req.put("user_id", user_id);
        return userService.bindEmail(req);
    }


    @PostMapping("/dynamics/list")
    public Result getDynamics(@ModelAttribute("user_id") Integer user_id,
                              @RequestBody Map<String, Object> req) {
        Integer page = (Integer) req.get("page");
        Integer size = (Integer) req.get("size");
        Integer find_id = (Integer) req.get("find_id");
        Integer type = (Integer) req.get("type");
        return userService.getDynamics(page,size,user_id,find_id,type);
    }


    /*第三方解绑*/
    @PostMapping("/oauth/unbind")
    public Result unbind(@ModelAttribute("user_id") Integer user_id,
                         @RequestBody Map<String, Object> req) {
        req.put("user_id", user_id);
        return userService.unbind(req);
    }

    /*加关注*/
    @PostMapping("/notice/add")
    public Result add(@RequestBody Notice req) {
        return noticeService.add(req);
    }

    /*取消关注*/
    @PostMapping("/notice/cancel")
    public Result cancel(@RequestBody Notice req) {
        return noticeService.cancel(req);
    }


    /*================================admin==========================*/
    @PostMapping("/list")
    public Result list(@RequestBody Map<String, Object> req) {
        Integer page = (Integer) req.get("page");
        Integer size = (Integer) req.get("size");
        String keyword = (String) req.get("keyword");
        return userService.getUserList(page, size, keyword);
    }

    @PostMapping("/info/manage")
    public Result infoManage(@ModelAttribute("user_id") Integer user_id,
                             @RequestBody User user) {
        if (user.getId().equals(user_id)) return Result.reject("对自己好点哦");
        return userService.updateInfo(user);
    }

    /*冻结用户*/
    @PostMapping("/freeze")
    public Result freeze(@ModelAttribute("user_id") Integer user_id,
                         @RequestBody Map<String, Object> req) {
        Integer id = (Integer) req.get("user_id");
        if (user_id.equals(id)) return Result.reject("对自己好点哦");
        return userService.freeze(id);
    }


}

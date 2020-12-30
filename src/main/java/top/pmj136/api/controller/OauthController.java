package top.pmj136.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiSnsGetuserinfoBycodeRequest;
import com.dingtalk.api.response.OapiSnsGetuserinfoBycodeResponse;
import com.taobao.api.ApiException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import top.pmj136.api.common.OauthUrl;
import top.pmj136.api.entity.User;
import top.pmj136.api.service.impl.UserServiceImpl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author 彭明久
 * @since 2020-10-20
 */
@Controller
@RequestMapping("/oauth")
public class OauthController {
    @Resource
    private UserServiceImpl userService;

    @Resource
    private OauthUrl oauthUrl;

    @GetMapping("/github/callback")
    public void githubCb(@ModelAttribute("user_id") Integer user_id,
                         @RequestParam("code") String code,
                         HttpServletResponse response) {
        String accessToken = getAccessToken("github", code);
        String userStr = getUserInfo("github", accessToken);
        Map<String, Object> userInfo = JSON.parseObject(userStr);
        Integer githubId = (Integer) userInfo.get("id");
        String nick = (String) userInfo.get("login");
        User findUser = userService.findUser("github_id", githubId);
        if (user_id == null) {//登录
            if (findUser == null) {
                User user = new User();
                user.setRole(1);
                user.setAvatarUrl((String) userInfo.get("avatar_url"));
                user.setNick(nick);
                user.setGithubId(githubId);
                user.setGithubNick(nick);
                Integer nId = userService.addUser(user);
                user.setId(nId);
                userService.handleLogin(user, "user");
            } else {
                userService.handleLogin(findUser, "user");
            }

            setRet(response, "授权成功");
        } else {//绑定
            if (findUser != null) {
                setRet(response, "已绑定其他邮箱，无法再绑定");
                return;
            }
            User user = new User();
            user.setId(user_id);
            user.setGithubId(githubId);
            user.setGithubNick(nick);
            userService.updateInfo(user);
            setRet(response, "绑定成功");
        }
    }

    @GetMapping("/gitee/callback")
    public void giteeCb(@ModelAttribute("user_id") Integer user_id,
                        @RequestParam("code") String code,
                        HttpServletResponse response) {
        String accessToken = getAccessToken("gitee", code);
        String userStr = getUserInfo("gitee", accessToken);
        Map<String, Object> userInfo = JSON.parseObject(userStr);
        Integer giteeId = (Integer) userInfo.get("id");
        String nick = (String) userInfo.get("login");
        User findUser = userService.findUser("gitee_id", giteeId);
        if (user_id == null) {
            if (findUser == null) {
                User user = new User();
                user.setRole(1);
                user.setAvatarUrl((String) userInfo.get("avatar_url"));
                user.setNick(nick);
                user.setGiteeId(giteeId);
                user.setGiteeNick(nick);
                Integer nId = userService.addUser(user);
                user.setId(nId);
                userService.handleLogin(user, "user");
            } else
                userService.handleLogin(findUser, "user");
            setRet(response, "授权成功");
        } else {
            if (findUser != null) {
                setRet(response, "已绑定其他邮箱，无法再绑定");
                return;
            }
            User user = new User();
            user.setId(user_id);
            user.setGiteeId(giteeId);
            user.setGiteeNick(nick);
            userService.updateInfo(user);
            setRet(response, "绑定成功");
        }
    }

    @GetMapping("/dingtalk/callback")
    public void dingtalkCb(@ModelAttribute("user_id") Integer user_id,
                           @RequestParam("code") String code,
                           HttpServletResponse response) {
        JSONObject dingtalkUserInfo = getDingtalkUserInfo(code);
        String openId = (String) dingtalkUserInfo.get("openid");
        String nick = (String) dingtalkUserInfo.get("nick");
        User findUser = userService.findUser("dingtalk_id", openId);
        if (user_id == null) {
            if (findUser == null) {
                User user = new User();
                user.setRole(1);
                user.setAvatarUrl("https://img.pmj136.top/avatar/ding.jpg");
                user.setNick(nick);
                user.setDingtalkId(openId);
                user.setDingtalkNick(nick);
                Integer nId = userService.addUser(user);
                user.setId(nId);
                userService.handleLogin(user, "user");
            } else
                userService.handleLogin(findUser, "user");
            setRet(response, "授权成功");
        } else {
            if (findUser != null) {
                setRet(response, "已绑定其他邮箱，无法再绑定");
                return;
            }
            User user = new User();
            user.setId(user_id);
            user.setDingtalkId(openId);
            user.setDingtalkNick(nick);
            userService.updateInfo(user);
            setRet(response, "绑定成功");
        }

    }

    private void setRet(HttpServletResponse response, String text) {
        String html = "<title>授权结果</title>" +
                "<script>" +
                "document.write('<div style=\"text-align:center;margin-top:2rem\">" + text + "</div>');" +
                "const timer=setTimeout(()=>{" +
                "clearTimeout(timer);" +
                "window.close();" +
                "},2000)" +
                "</script>";
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().println(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getAccessToken(String type, String code) {
        String url = oauthUrl.getGithubAccessUrl(code);
        if (type.equals("gitee")) url = oauthUrl.getGiteeAccessUrl(code);
        // 构建请求头
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("accept", "application/json");
        if (type.equals("gitee"))
            requestHeaders.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 ");
        // 构建请求实体
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
        RestTemplate restTemplate = new RestTemplate();

        // post 请求方式
        ResponseEntity<String> response = restTemplate.postForEntity(
                url,
                requestEntity,
                String.class);
        String responseStr = response.getBody();

        // 解析响应json字符串
        JSONObject jsonObject = JSONObject.parseObject(responseStr);
        return jsonObject.getString("access_token");
    }

    /*获取钉钉用户信息*/
    private JSONObject getDingtalkUserInfo(String code) {
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/sns/getuserinfo_bycode");
        OapiSnsGetuserinfoBycodeRequest req = new OapiSnsGetuserinfoBycodeRequest();
        req.setTmpAuthCode(code);
        OapiSnsGetuserinfoBycodeResponse response = null;
        try {
            response = client.execute(req, oauthUrl.getDingtalk_app_id(), oauthUrl.getDingtalk_app_secret());
        } catch (ApiException e) {
            e.printStackTrace();
        }
        JSONObject json = JSONObject.parseObject(response.getBody());
        return JSONObject.parseObject(JSONObject.toJSONString(json.get("user_info")));
    }

    /*获取github、gitee用户信息*/
    private String getUserInfo(String type, String accessToken) {
        String url = oauthUrl.getGithubInfoUrl(accessToken);
        if (type.equals("gitee")) url = oauthUrl.getGiteeInfoUrl(accessToken);
        // 构建请求头
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("accept", "application/json");
        requestHeaders.add("Authorization", "token " + accessToken);

        if (type.equals("gitee"))
            requestHeaders.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 ");

        // 构建请求实体
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);

        RestTemplate restTemplate = new RestTemplate();
        // get请求方式
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET, requestEntity,
                String.class);
        return response.getBody();
    }
}

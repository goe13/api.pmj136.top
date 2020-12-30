package top.pmj136.api.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.pmj136.api.util.RedisUtil;
import top.pmj136.api.util.Result;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 彭明久
 * @since 2020-10-01
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Resource
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        boolean flag = false;
        String token = null;
        String msg = "请先登录";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
            if (token != null) {
                Object redisData = redisUtil.get(token);
                if (redisData == null){
                    flag = false;
                    msg = "用户验证失败，需重新登录!";
                }
                else {
                    Map userData = JSONObject.parseObject(JSONObject.toJSONString(redisData), Map.class);
                    flag = userData.get(getClient(request)) != null;
                }
            }
        }
        if (!flag) {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(200);
            response.getWriter().write(Result.build(401, msg, null).toString());
        }
        return flag;
    }


    private String getClient(HttpServletRequest request) {
        String origin = request.getHeader("Origin");
        if (isAdminClient(origin)) return "admin";
        return "user";
    }

    private Boolean isAdminClient(String origin) {
        return "http://localhost:8527".equals(origin) || "https://manage.pmj136.top".equals(origin);
    }
}

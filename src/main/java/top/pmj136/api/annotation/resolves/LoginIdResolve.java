package top.pmj136.api.annotation.resolves;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import top.pmj136.api.annotation.LoginId;
import top.pmj136.api.exception.AppException;
import top.pmj136.api.component.RedisHelper;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * @author 彭明久
 * @since 2020-12-31
 */
public class LoginIdResolve implements HandlerMethodArgumentResolver {

    @Resource
    private RedisHelper redisHelper;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(LoginId.class);
    }

    @Override
    public Integer resolveArgument(MethodParameter methodParameter,
                                   ModelAndViewContainer modelAndViewContainer,
                                   NativeWebRequest nativeWebRequest,
                                   WebDataBinderFactory webDataBinderFactory
    ) throws Exception {
        boolean required = methodParameter.getParameter().getAnnotation(LoginId.class).required();
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        Cookie[] cookies = request != null ? request.getCookies() : new Cookie[0];
        if (cookies == null) {
            if (required) throw new AppException(401, "请先登录");
            return null;
        }
        String token = null;
        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }

        if (token == null) {
            if (required) throw new AppException(401, "请先登录");
            return null;
        }
        if (!redisHelper.hasKey(token)) {
            if (required) throw new AppException(401, "用户验证失败，请重新登录");
            return null;
        }
        Map userData = JSONObject.parseObject(JSONObject.toJSONString(redisHelper.get(token)), Map.class);
        //验证客户端
        if (userData.get(getClient(request)) == null) {
            if (required) throw new AppException(401, "请先登录");
            return null;
        }
        return (Integer) userData.get("user_id");
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

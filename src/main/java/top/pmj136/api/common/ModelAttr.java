package top.pmj136.api.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import top.pmj136.api.util.RedisUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author 彭明久
 * @since 2020-10-22
 */
@ControllerAdvice
public class ModelAttr {
    @Resource
    private RedisUtil redisUtil;

    @ModelAttribute("user_id")
    public Integer userId(@CookieValue(value = "token", required = false) String token,
                          HttpServletRequest request) {
        if (token == null) return null;

        Object redisData = redisUtil.get(token);
        if (redisData == null) return null;

        Map userData = JSONObject.parseObject(JSONObject.toJSONString(redisData), Map.class);
        //验证客户端
        if(userData.get(getClient(request)) == null) return null;

        return (Integer) userData.get("user_id");
    }

    @ModelAttribute("client")
    public String client(HttpServletRequest request) {
        return getClient(request);
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

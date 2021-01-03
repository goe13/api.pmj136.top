package top.pmj136.api.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 彭明久
 * @since 2020-09-28
 */
@Component
public class CookieHelper {
    @Value("${api.cookie.domain}")
    private String domain;
    @Value("${api.cookie.http-only:false}")
    private Boolean httpOnly;
    @Value("${api.cookie.expire:-1}")
    private Integer expire;

    @Resource
    private HttpServletResponse response;

    /**
     * 设置Cookie
     */
    public void set(String pro, String val) {
        Cookie cookie = new Cookie(pro, val);
        cookie.setPath("/");
        cookie.setMaxAge(expire);
        cookie.setHttpOnly(httpOnly);
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }
}

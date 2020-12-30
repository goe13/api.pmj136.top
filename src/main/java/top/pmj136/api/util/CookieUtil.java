package top.pmj136.api.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 彭明久
 * @since 2020-09-28
 */
@Component
public class CookieUtil {
    @Value("${api.cookie.domain}")
    private String domain1;
    @Value("${api.cookie.http-only:false}")
    private Boolean httpOnly1;
    @Value("${api.cookie.expire:-1}")
    private Integer expire1;

//    @Resource
//    private HttpServletRequest request1;

    @Resource
    private HttpServletResponse response1;

//    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static String domain;
    private static Boolean httpOnly;
    private static Integer expire;

    @PostConstruct
    public void init() {
//        request = request1;
        response = response1;
        domain = domain1;
        httpOnly = httpOnly1;
        expire = expire1;
    }

    /**
     * 获取浏览Cookie中的属性值
     */
//    public static String get(String pro) {
//        String ret = null;
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals(pro)) ret = cookie.getValue();
//            }
//        }
//        return ret;
//    }

    /**
     * 设置Cookie
     */
    public static void set(String pro, String val) {
        Cookie cookie = new Cookie(pro, val);
        cookie.setPath("/");
        cookie.setMaxAge(expire);
        cookie.setHttpOnly(httpOnly);
        cookie.setDomain(domain);
        response.addCookie(cookie);
    }
}

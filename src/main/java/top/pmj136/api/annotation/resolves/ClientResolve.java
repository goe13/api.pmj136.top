package top.pmj136.api.annotation.resolves;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import top.pmj136.api.annotation.Client;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 彭明久
 * @since 2021-01-03
 */
public class ClientResolve implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(Client.class);
    }

    @Override
    public String resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
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

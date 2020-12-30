package top.pmj136.api.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author 彭明久
 * @since 2020-10-01
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private UserInterceptor userInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*不需要权限验证*/
        String[] excludePath = {
                "/oauth/**",
                "/app/index",
                "/msg/count",
                "/article/recommend",
                "/article/detail",
                "/article/list",
                "/article/search",
                "/remark/root/list",
                "/user/login",
                "/user/auth",
                "/user/get",
                "/user/email/check",
                "/user/code/post",
                "/user/dynamics/list"
        };
        registry.addInterceptor(userInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
    }
}

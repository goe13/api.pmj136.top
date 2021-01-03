package top.pmj136.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.pmj136.api.annotation.resolves.ClientResolve;
import top.pmj136.api.annotation.resolves.LoginIdResolve;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 彭明久
 * @since 2020-10-01
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Bean
    public HandlerMethodArgumentResolver LoginIdResolve() {
        return new LoginIdResolve();
    }

    @Bean
    public HandlerMethodArgumentResolver ClientResolve() {
        return new ClientResolve();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        List<HandlerMethodArgumentResolver> list = new ArrayList<>();
        list.add(LoginIdResolve());
        list.add(ClientResolve());
        resolvers.addAll(list);
    }
}

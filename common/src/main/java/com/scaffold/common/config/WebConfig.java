package com.scaffold.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @Author danyiran
 * @create 2020/7/1 22:15
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Value("${cors.enable}")
    private boolean enableCORS;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (enableCORS) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods(HttpMethod.GET.name(),
                            HttpMethod.POST.name(),
                            HttpMethod.PUT.name(),
                            HttpMethod.DELETE.name());
        }
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
    }
}

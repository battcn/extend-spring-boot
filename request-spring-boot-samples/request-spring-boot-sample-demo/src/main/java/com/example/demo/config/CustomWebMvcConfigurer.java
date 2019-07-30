package com.example.storage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author kc
 * @since 2018/9/13 18:15
 */
@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private RequestLoggingHandler requestLoggingHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestLoggingHandler);
    }
}

package com.battcn.boot.request.configuration.servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * QQ交流群：391619659
 *
 * @author Levin
 * @since 2018-12-17
 */
public class RequestWrapperFilter {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean<BodyCacheFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new BodyCacheFilter());
        return registrationBean;
    }


    private static class BodyCacheFilter extends OncePerRequestFilter {
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            final String method = request.getMethod();
            // 如果不是 POST PATCH PUT 等有流的接口则无需进行类型转换，提高性能
            if (HttpMethod.POST.matches(method) || HttpMethod.PATCH.matches(method) || HttpMethod.PUT.matches(method)) {
                if (!(request instanceof BodyCacheHttpServletRequestWrapper)) {
                    request = new BodyCacheHttpServletRequestWrapper(request);
                }
            }
            if (!(response instanceof BodyCacheHttpServletResponseWrapper)) {
                response = new BodyCacheHttpServletResponseWrapper(response);
            }
            filterChain.doFilter(request, response);
        }
    }
}

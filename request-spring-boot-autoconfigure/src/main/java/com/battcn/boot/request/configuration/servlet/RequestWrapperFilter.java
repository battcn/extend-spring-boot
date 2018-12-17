package com.battcn.boot.request.configuration.servlet;

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
public class RequestWrapperFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!(request instanceof BodyCacheHttpServletRequestWrapper)) {
            request = new BodyCacheHttpServletRequestWrapper(request);
        }
        if (!(response instanceof BodyCacheHttpServletResponseWrapper)) {
            response = new BodyCacheHttpServletResponseWrapper(response);
        }
        filterChain.doFilter(request, response);
    }
}

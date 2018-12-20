package com.example.demo.config;

import com.battcn.boot.request.configuration.servlet.BodyCacheHttpServletRequestWrapper;
import com.battcn.boot.request.configuration.servlet.BodyCacheHttpServletResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Levin
 */
@Configuration
public class RequestLoggingHandler implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingHandler.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String method = request.getMethod();
        if (HttpMethod.POST.matches(method) || HttpMethod.PATCH.matches(method) || HttpMethod.PUT.matches(method)) {
            if (request instanceof BodyCacheHttpServletRequestWrapper) {
                BodyCacheHttpServletRequestWrapper requestWrapper = ((BodyCacheHttpServletRequestWrapper) request);
                logger.info("[请求] - [{}]", new String(requestWrapper.getBody()));
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        final String method = request.getMethod();
        if (HttpMethod.POST.matches(method) || HttpMethod.PATCH.matches(method) || HttpMethod.PUT.matches(method)) {
            if (response instanceof BodyCacheHttpServletResponseWrapper) {
                BodyCacheHttpServletResponseWrapper responseWrapper = ((BodyCacheHttpServletResponseWrapper) response);
                logger.info("[请求] - [{}]", new String(responseWrapper.getBody()));
            }
        }
    }

}

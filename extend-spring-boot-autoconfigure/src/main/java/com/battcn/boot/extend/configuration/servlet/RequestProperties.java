package com.battcn.boot.extend.configuration.servlet;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Levin
 * @since 2018/12/17 0017
 */
@Data
@ConfigurationProperties(prefix = "extend.filter")
public class RequestProperties {

    private int order;
    private String name = "extend-filter";
    /**
     * Flag to indicate that the registration is enabled.
     */
    private Boolean enabled = true;
    private Map<String, String> initParameters = new LinkedHashMap<>();
    private Set<String> servletNames = new LinkedHashSet<>();
    private Set<ServletRegistrationBean<?>> servletRegistrationBeans = new LinkedHashSet<>();
    private Set<String> urlPatterns = new LinkedHashSet<>();

}

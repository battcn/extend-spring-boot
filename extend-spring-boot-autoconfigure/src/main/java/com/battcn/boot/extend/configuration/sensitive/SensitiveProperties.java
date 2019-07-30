package com.battcn.boot.extend.configuration.sensitive;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Levin
 * @since 2019/1/2 0002
 */
@Data
@ConfigurationProperties("extend.sensitive")
public class SensitiveProperties {

    private String encoding = "UTF-8";
    /**
     * 字典存在路径
     */
    private String dictFilePath;

    /**
     * 和谐的字符
     */
    private String replacement = "*";

    private Filter filter = new Filter();

    @Data
    @ConfigurationProperties("extend.sensitive.filter")
    public static class Filter {

        private int order;
        private String name = "sensitive-filter";
        private Map<String, String> initParameters = new LinkedHashMap<>();
        private Set<String> servletNames = new LinkedHashSet<>();
        private Set<ServletRegistrationBean<?>> servletRegistrationBeans = new LinkedHashSet<>();
        private Set<String> urlPatterns = new LinkedHashSet<>();
    }


}

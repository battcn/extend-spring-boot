package com.battcn.boot.request.configuration.sensitive;

import com.battcn.boot.request.utils.sensitive.SensitiveProcessor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

/**
 * @author Levin
 * @since 2019/1/2 0002
 */
@EnableConfigurationProperties(value = {SensitiveProperties.class})
public class SensitiveAutoConfiguration {

    private static final SensitiveProcessor PROCESSOR = SensitiveProcessor.getInstance();

    @Bean("registrationSensitiveFilter")
    public FilterRegistrationBean registrationSensitiveFilter(SensitiveProperties properties) throws IOException {
        final URL resource = getClass().getClassLoader().getResource(properties.getDictFilePath());
        if (resource == null) {
            throw new NullPointerException("dict file not found");
        }
        String path = resource.getPath();
        boolean relative = path.startsWith("/");
        if (relative) {
            path = path.replaceFirst("/", "");
        }
        // 加载数据字典
        PROCESSOR.loadWordDict(path, Charset.forName(properties.getEncoding()));

        FilterRegistrationBean<SensitiveFilter> registrationBean = new FilterRegistrationBean<>();
        // 设置 SensitiveFilter
        registrationBean.setFilter(new SensitiveFilter(properties));
        final SensitiveProperties.Filter filter = properties.getFilter();
        if (filter != null) {
            // 设置过滤路径
            registrationBean.setEnabled(filter.getEnabled());
            // 设置顺序
            registrationBean.setOrder(filter.getOrder());
            final String name = filter.getName();
            if (!StringUtils.isEmpty(name)) {
                registrationBean.setName(filter.getName());
            }
            final Map<String, String> initParameters = filter.getInitParameters();
            if (initParameters != null && initParameters.size() > 0) {
                registrationBean.setInitParameters(filter.getInitParameters());
            }
            final Set<ServletRegistrationBean<?>> registrationBeans = filter.getServletRegistrationBeans();
            if (registrationBeans != null && registrationBeans.size() > 0) {
                registrationBean.setServletRegistrationBeans(filter.getServletRegistrationBeans());
            }
            final Set<String> servletNames = filter.getServletNames();
            if (!CollectionUtils.isEmpty(servletNames)) {
                registrationBean.setServletNames(filter.getServletNames());
            }
        }
        return registrationBean;
    }
}

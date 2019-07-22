package com.battcn.boot.request.configuration.mybatis.plugins;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * mybatis 预备拦截器处理插件
 *
 * @author Levin
 */
@Configuration
@EnableConfigurationProperties(PreparePluginProperties.class)
public class PreparePluginsConfiguration {

    @Bean
    public PrepareInterceptor prepareInterceptor(PreparePluginProperties preparePluginProperties,
                                                 PreparePluginContent preparePluginContent) {
        return new PrepareInterceptor(preparePluginProperties, preparePluginContent);
    }
}
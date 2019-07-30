package com.battcn.boot.extend.configuration.mybatis.plugins;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;


/**
 * mybatis 预备拦截器处理插件
 *
 * @author Levin
 */
@Configuration
@EnableConfigurationProperties(PreparePluginProperties.class)
public class PreparePluginAutoConfiguration {

    @Bean
    public PrepareInterceptor prepareInterceptor(PreparePluginProperties preparePluginProperties,
                                                 @NotNull PreparePluginContent preparePluginContent) {
        return new PrepareInterceptor(preparePluginProperties, preparePluginContent);
    }
}
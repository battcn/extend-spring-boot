package com.battcn.boot.request.annotation;


import com.battcn.boot.request.configuration.mybatis.plugins.PreparePluginsConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * mybatis 值插入插件
 *
 * @author Levin
 * @since 2018-01-15
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({PreparePluginsConfiguration.class})
public @interface EnableMybatisPerparePlugin {
}

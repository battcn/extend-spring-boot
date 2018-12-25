package com.battcn.boot.request.annotation;

import com.battcn.boot.request.configuration.xss.XssFilterAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Xss 过滤保护
 *
 * @author Levin
 * @since 2018-01-15
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({XssFilterAutoConfiguration.class})
public @interface EnableXssFilter {

}

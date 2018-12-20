package com.battcn.boot.request.annotation;

import com.battcn.boot.request.configuration.cors.CorsFilterAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Cors 过滤
 *
 * @author Levin
 * @since 2018-01-15
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CorsFilterAutoConfiguration.class})
public @interface EnableCorsFilter {

}

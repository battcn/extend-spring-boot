package com.battcn.boot.request.annotation;

import com.battcn.boot.request.configuration.cors.CorsFilterAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启跨域支持
 *
 * @author Levin
 * @since 2019-01-01
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CorsFilterAutoConfiguration.class})
public @interface EnableCorsFilter {

}

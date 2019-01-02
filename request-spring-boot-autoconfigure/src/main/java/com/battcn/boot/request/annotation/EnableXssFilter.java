package com.battcn.boot.request.annotation;

import com.battcn.boot.request.configuration.commons.CommonAutoConfiguration;
import com.battcn.boot.request.configuration.xss.XssFilterAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启防 Xss 攻击
 *
 * @author Levin
 * @since 2018-01-15
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({XssFilterAutoConfiguration.class, CommonAutoConfiguration.class})
public @interface EnableXssFilter {

}

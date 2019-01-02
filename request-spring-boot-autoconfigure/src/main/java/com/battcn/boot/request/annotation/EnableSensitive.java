package com.battcn.boot.request.annotation;

import com.battcn.boot.request.configuration.commons.CommonAutoConfiguration;
import com.battcn.boot.request.configuration.sensitive.SensitiveAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启敏感字符过滤
 *
 * @author Levin
 * @since 2019/1/2 0002
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({SensitiveAutoConfiguration.class, CommonAutoConfiguration.class})
public @interface EnableSensitive {


}

package com.battcn.boot.request.annotation;

import com.battcn.boot.request.configuration.time.LocalDateSerializerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启 JDK8 日期格式化
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Documented
@Import(LocalDateSerializerConfig.class)
public @interface EnableLocalDateTimeFormat {


}

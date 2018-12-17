package com.battcn.boot.request.annotation;

import com.battcn.boot.request.configuration.time.LocalDateSerializerConfig;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Documented
@Import(LocalDateSerializerConfig.class)
public @interface EnableLocalDateTimeFormat {


}

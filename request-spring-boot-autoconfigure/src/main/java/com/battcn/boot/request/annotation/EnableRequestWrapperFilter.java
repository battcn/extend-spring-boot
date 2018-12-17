package com.battcn.boot.request.annotation;

import com.battcn.boot.request.configuration.servlet.RequestWrapperFilter;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Documented
@Import(RequestWrapperFilter.class)
public @interface EnableRequestWrapperFilter {


}

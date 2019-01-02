package com.battcn.boot.request.annotation;

import com.battcn.boot.request.configuration.servlet.RequestWrapperFilter;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启 Request/Response 多次读取
 *
 * @author Levin
 * @since 2019-01-01
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Documented
@Import(RequestWrapperFilter.class)
public @interface EnableRequestWrapperFilter {


}

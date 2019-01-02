package com.battcn.boot.request.annotation;

import com.battcn.boot.request.configuration.i18n.I18nAutoConfiguration;
import com.battcn.boot.request.configuration.i18n.LocaleMessage;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启国际化支持
 *
 * @author Levin
 * @since 2018-01-15
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({I18nAutoConfiguration.class, LocaleMessage.class})
public @interface EnableI18n {

}

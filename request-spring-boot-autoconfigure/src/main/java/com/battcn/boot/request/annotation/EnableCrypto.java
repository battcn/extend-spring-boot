package com.battcn.boot.request.annotation;

import com.battcn.boot.request.configuration.crypto.CryptoAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启加/解密功能
 *
 * @author Levin
 * @since 2018/12/28 0028
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CryptoAutoConfiguration.class})
public @interface EnableCrypto {
}

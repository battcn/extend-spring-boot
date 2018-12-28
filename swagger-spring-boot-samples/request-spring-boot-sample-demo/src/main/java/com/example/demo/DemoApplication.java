package com.example.demo;

import com.battcn.boot.request.annotation.*;
import com.battcn.boot.request.configuration.crypto.CryptoProperties;
import com.battcn.boot.request.configuration.crypto.advice.DecryptRequestBodyAdvice;
import com.battcn.boot.request.configuration.crypto.advice.EncryptResponseBodyAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author Levin
 */
@EnableCrypto
@EnableI18n
//@EnableXssFilter
@EnableCorsFilter
//@EnableLocalDateTimeFormat
//@EnableRequestWrapperFilter
@EnableRedisLimit
@EnableRedisLock
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}


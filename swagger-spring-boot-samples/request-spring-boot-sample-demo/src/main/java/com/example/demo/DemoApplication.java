package com.example.demo;

import com.battcn.boot.request.annotation.*;
import com.battcn.boot.request.configuration.encrypt.EncryptProperties;
import com.battcn.boot.request.configuration.encrypt.advice.DecryptRequestBodyAdvice;
import com.battcn.boot.request.configuration.encrypt.advice.EncryptResponseBodyAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author Levin
 */
@Import({DecryptRequestBodyAdvice.class, EncryptProperties.class, EncryptResponseBodyAdvice.class})
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


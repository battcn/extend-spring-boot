package com.example.storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Levin
 */
@EnableCrypto
@EnableSensitive
@EnableI18n
@EnableXssFilter
@EnableCorsFilter
@EnableLocalDateTimeFormat
@EnableRequestWrapperFilter
@EnableRedisLimit
@EnableRedisLock
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}


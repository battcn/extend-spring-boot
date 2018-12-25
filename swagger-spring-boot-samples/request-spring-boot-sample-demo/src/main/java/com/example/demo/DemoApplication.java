package com.example.demo;

import com.battcn.boot.request.annotation.EnableCorsFilter;
import com.battcn.boot.request.annotation.EnableI18n;
import com.battcn.boot.request.annotation.EnableLocalDateTimeFormat;
import com.battcn.boot.request.annotation.EnableRedisLimit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Levin
 */
@EnableI18n
@EnableCorsFilter
@EnableLocalDateTimeFormat
//@EnableRequestWrapperFilter
@EnableRedisLimit
//@EnableRedisLock
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}


package com.example.demo;

import com.battcn.boot.request.annotation.EnableLocalDateTimeFormat;
import com.battcn.boot.request.annotation.EnableRequestWrapperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Levin
 */
@EnableLocalDateTimeFormat
@EnableRequestWrapperFilter
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}


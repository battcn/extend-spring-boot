package com.example.storage.controller;

import com.example.storage.entity.TestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author Levin
 * @since 2018/12/17 0017
 */

@RequestMapping("/test")
@RestController
public class TestController {


    @PostMapping
    public TestBody testBody(@RequestBody TestBody testBody) {
        testBody.setTime(LocalDateTime.now());
        return testBody;
    }


}

package com.example.storage.controller;

import com.example.storage.entity.TestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 * @since 2018/12/25 0025
 */
@RestController
@RequestMapping("/xss")
public class XssController {


    @PostMapping
    public TestBody testXss(@RequestBody TestBody body) {
        return body;
    }


}

package com.example.storage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Levin
 * @since 2018/12/20 0020
 */
@RestController
public class CorsController {

    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }

}

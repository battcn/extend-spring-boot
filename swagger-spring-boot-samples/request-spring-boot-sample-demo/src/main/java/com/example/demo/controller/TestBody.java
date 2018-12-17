package com.example.demo.controller;

import java.time.LocalDateTime;

/**
 * @author Levin
 * @since 2018/12/17 0017
 */
public class TestBody {


    private Long id;
    private String name;
    private LocalDateTime time;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}

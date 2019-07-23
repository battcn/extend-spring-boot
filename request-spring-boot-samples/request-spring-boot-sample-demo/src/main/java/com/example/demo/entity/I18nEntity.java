package com.example.demo.entity;

import javax.validation.constraints.NotBlank;

/**
 * @author Levin
 * @since 2018/12/20 0020
 */
public class I18nEntity {

    @NotBlank(message = "{vo.myerror.name}")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

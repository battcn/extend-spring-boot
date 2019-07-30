package com.example.storage.controller;

import com.battcn.boot.request.configuration.i18n.LocaleMessage;
import com.example.storage.entity.I18nEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Levin
 * @since 2018/12/20 0020
 */
@RestController
@RequestMapping("/i18n")
public class I18nController {


    private final LocaleMessage localeMessage;

    @Autowired
    public I18nController(LocaleMessage localeMessage) {
        this.localeMessage = localeMessage;
    }


    @GetMapping
    public String index() {
        return localeMessage.getMessage("welcome.error");
    }

    @PostMapping
    public I18nEntity index(@Valid I18nEntity i18) {
        return i18;
    }


}

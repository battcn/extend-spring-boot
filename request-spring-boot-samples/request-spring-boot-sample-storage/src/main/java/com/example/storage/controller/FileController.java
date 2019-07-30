package com.example.storage.controller;

import com.battcn.boot.request.configuration.storage.StorageOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Levin
 */
@AllArgsConstructor
@RestController
public class FileController {

    @Autowired
    private StorageOperation storageOperation;


    @PostMapping
    public String upload(@RequestParam MultipartFile file) throws IOException {
        storageOperation.upload(file.getOriginalFilename(), file.getBytes());
        return file.getOriginalFilename();
    }


}

package com.battcn.boot.request.configuration.storage.domain;

import lombok.Data;

import java.util.Map;

/**
 * @author Levin
 */
@Data
public class StorageItem {

    /**
     * 文件名
     */
    private String name;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件扩展字段
     */
    private Map<String, Object> extended;


}

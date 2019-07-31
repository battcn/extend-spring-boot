package com.battcn.boot.extend.configuration.oss.domain;


import lombok.Data;

/**
 * 响应结果
 *
 * @author Levin
 */
@Data
public class StorageResponse implements java.io.Serializable {

    private static final long serialVersionUID = -498222912510624959L;

    private boolean successful;
    private String message;
    private StorageItem storageItem;

}

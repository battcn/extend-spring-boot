package com.battcn.boot.extend.configuration.oss.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Levin
 */
@Data
public class BaseStorageProperties implements java.io.Serializable {

    private static final long serialVersionUID = 3372604246046939938L;
    private boolean enabled = false;


    @Value("${spring.application.name:'oss'}")
    private String bucket;

    private String mapperPath = "http://blog.battcn.com/";

    /**
     * 访问key
     **/
    private String accessKey;

    /**
     * 访问秘钥
     **/
    private String secretKey;


    public enum StorageType {

        /**
         * MINIO
         */
        MINIO,
        ALIYUN,
        QCLOUD,
        QINIU,
        ;
    }


}

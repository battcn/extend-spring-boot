package com.battcn.boot.request.configuration.storage.domain;

/**
 * @author Levin
 * @since 2019-07-30
 **/
public interface OssConstants {
    /**
     * 配置文件前缀
     */
    String OSS_CONFIG_PREFIX_ALIYUN = "request.storage.oss.aliyun";
    String OSS_CONFIG_PREFIX_MINIO = "request.storage.oss.minio";
    String OSS_CONFIG_PREFIX_QINIU = "request.storage.oss.qiniu";
    String OSS_CONFIG_PREFIX_TENCENT = "request.storage.oss.tencent";


    String MINIO_STORAGE_OPERATION = "minioStorageOperation";
    String ALI_YUN_STORAGE_OPERATION = "aliYunStorageOperation";
    String QI_NIU_STORAGE_OPERATION = "qiNiuStorageOperation";
    String TENCENT_STORAGE_OPERATION = "tencentStorageOperation";





}
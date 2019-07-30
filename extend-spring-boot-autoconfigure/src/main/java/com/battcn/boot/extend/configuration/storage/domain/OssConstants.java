package com.battcn.boot.extend.configuration.storage.domain;

/**
 * @author Levin
 * @since 2019-07-30
 **/
public interface OssConstants {
    /**
     * 配置文件前缀
     */
    String OSS_CONFIG_PREFIX_ALIYUN = "extend.storage.oss.aliyun";
    String OSS_CONFIG_PREFIX_MINIO = "extend.storage.oss.minio";
    String OSS_CONFIG_PREFIX_QINIU = "extend.storage.oss.qiniu";
    String OSS_CONFIG_PREFIX_TENCENT = "extend.storage.oss.tencent";


    String MINIO_STORAGE_OPERATION = "minioStorageOperation";
    String ALI_YUN_STORAGE_OPERATION = "aliYunStorageOperation";
    String QI_NIU_STORAGE_OPERATION = "qiNiuStorageOperation";
    String TENCENT_STORAGE_OPERATION = "tencentStorageOperation";





}
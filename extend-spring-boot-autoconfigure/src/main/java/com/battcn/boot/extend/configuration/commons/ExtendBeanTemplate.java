package com.battcn.boot.extend.configuration.commons;

/**
 * 扩展包所有 bean 名字
 *
 * @author Levin
 * @since 2019-07-30
 **/
public interface ExtendBeanTemplate {
















    /*  OSS 云存储的    */
    /**
     * 配置文件前缀
     */
    String OSS_CONFIG_PREFIX_ALIYUN = "extend.oss.cloud.aliyun";
    String OSS_CONFIG_PREFIX_MINIO = "extend.oss.cloud.minio";
    String OSS_CONFIG_PREFIX_QINIU = "extend.oss.cloud.qiniu";
    String OSS_CONFIG_PREFIX_TENCENT = "extend.oss.cloud.tencent";


    String MINIO_STORAGE_OPERATION = "minioStorageOperation";
    String ALI_YUN_STORAGE_OPERATION = "aliYunStorageOperation";
    String QI_NIU_STORAGE_OPERATION = "qiNiuStorageOperation";
    String TENCENT_STORAGE_OPERATION = "tencentStorageOperation";


}
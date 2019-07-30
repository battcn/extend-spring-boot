package com.battcn.boot.request.configuration.storage.properties;

import com.battcn.boot.request.configuration.storage.domain.OssConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Levin
 * @since 2018-09-17 11:09
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = OssConstants.OSS_CONFIG_PREFIX_TENCENT)
public class TencentStorageProperties extends BaseStorageProperties {

    private static final long serialVersionUID = -4336190295449006211L;

    /**
     * AppId
     */
    private String appId;
    /**
     * 前缀
     */
    private String prefix;

    /**
     * 绑定的域名
     */
    private String domain;

    /**
     * 所属地区
     */
    private String region;
}
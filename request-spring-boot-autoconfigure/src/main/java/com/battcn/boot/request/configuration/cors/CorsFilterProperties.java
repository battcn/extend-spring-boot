package com.battcn.boot.request.configuration.cors;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;

/**
 * @author Levin
 * @create 2017/12/5 0005
 */
@Data
@ConfigurationProperties("request.cors")
public class CorsFilterProperties {

    private Boolean enabled;
    private String path;
    private String origin;
    private String allowedHeader;
    private String method;
    private String exposedHeader;

    @Nullable
    private Boolean allowCredentials;

    @Nullable
    private Long maxAge;

}

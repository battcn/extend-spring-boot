package com.battcn.boot.extend.configuration.i18n;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 国际化资源配置属性
 *
 * @author Levin
 * @since 2017/12/5 0005
 */
@Data
@ConfigurationProperties("extend.i18n")
public class I18nProperties {

    /**
     * 默认 UTF-8
     */
    private String defaultEncoding;
    private String[] baseNames;

}

package com.battcn.boot.extend.configuration.mybatis.plugins;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * @author Levin
 */
@Data
@Validated
@ConfigurationProperties(prefix = "extend.mybatis.plugin")
public class PreparePluginProperties {

    private Boolean superClass = false;
    private ClassField insert;
    private ClassField update;


    @Data
    public static class ClassField {
        private List<String> fields;
    }
}

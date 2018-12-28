package com.battcn.boot.request.configuration.crypto;

import com.battcn.boot.request.utils.encrypt.CryptoType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Levin
 * @since 2018/12/27 0027
 */
@Data
@Validated
@ConfigurationProperties(prefix = "request.crypto")
public class CryptoProperties {

    private Encrypt encrypt;
    private Decrypt decrypt;
    private String encoding = "UTF-8";

    @Data
    @ConfigurationProperties(prefix = "request.crypto.encrypt")
    public static class Encrypt {
        @NotNull
        private String key;
        @NotNull
        private CryptoType type;
    }

    @Data
    @ConfigurationProperties(prefix = "request.crypto.decrypt")
    public static class Decrypt {

        @NotNull
        private String key;
        @NotNull
        private CryptoType type;
    }


}

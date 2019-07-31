package com.battcn.boot.extend.configuration.crypto;

import com.battcn.boot.extend.configuration.crypto.advice.DecryptRequestBodyAdvice;
import com.battcn.boot.extend.configuration.crypto.advice.EncryptResponseBodyAdvice;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Levin
 * @since 2018/12/28 0028
 */
@Configuration
@Import({DecryptRequestBodyAdvice.class, CryptoProperties.class, EncryptResponseBodyAdvice.class})
public class CryptoAutoConfiguration {


}

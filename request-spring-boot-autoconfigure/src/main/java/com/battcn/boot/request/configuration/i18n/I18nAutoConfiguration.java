package com.battcn.boot.request.configuration.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

import static com.battcn.boot.request.utils.StringUtils.DEFAULT_CHARSET;
import static com.battcn.boot.request.utils.StringUtils.defaultString;

/**
 * @author Levin
 * @since 2018/2/1 0001
 */
@Configuration
@EnableConfigurationProperties(value = {I18nProperties.class})
public class I18nAutoConfiguration {

    private final I18nProperties properties;

    @Autowired
    public I18nAutoConfiguration(I18nProperties properties) {
        this.properties = properties;
    }

    private ResourceBundleMessageSource getMessageSource() throws Exception {
        ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
        bundleMessageSource.setDefaultEncoding(defaultString(properties.getDefaultEncoding(), DEFAULT_CHARSET));
        bundleMessageSource.setBasenames(properties.getBaseNames());
        return bundleMessageSource;
    }

    @Bean
    public Validator getValidator() throws Exception {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(getMessageSource());
        return validator;
    }
}

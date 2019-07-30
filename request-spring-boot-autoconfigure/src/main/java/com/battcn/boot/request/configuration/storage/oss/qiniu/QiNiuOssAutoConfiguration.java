package com.battcn.boot.request.configuration.storage.oss.qiniu;

import com.battcn.boot.request.configuration.storage.QiNiuStorageOperation;
import com.battcn.boot.request.configuration.storage.domain.OssConstants;
import com.battcn.boot.request.configuration.storage.oss.qiniu.connection.QiNiuConnectionFactory;
import com.battcn.boot.request.configuration.storage.oss.qiniu.connection.QiNiuOssClientConnectionFactory;
import com.battcn.boot.request.configuration.storage.properties.QiNiuStorageProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import static com.battcn.boot.request.configuration.storage.domain.OssConstants.QI_NIU_STORAGE_OPERATION;

/**
 * @author Levin
 */
@EnableConfigurationProperties({QiNiuStorageProperties.class})
@ConditionalOnProperty(prefix = OssConstants.OSS_CONFIG_PREFIX_QINIU, name = "enabled", havingValue = "true")
public class QiNiuOssAutoConfiguration {

    @Bean
    public QiNiuConnectionFactory qiNiuConnectionFactory(QiNiuStorageProperties properties) {
        return new QiNiuOssClientConnectionFactory(properties);
    }

    @Bean(QI_NIU_STORAGE_OPERATION)
    public QiNiuStorageOperation qiNiuStorageOperation(QiNiuStorageProperties properties, QiNiuConnectionFactory qiNiuConnectionFactory) {
        return new QiNiuStorageOperation(properties, qiNiuConnectionFactory);
    }
}
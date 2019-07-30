package com.battcn.boot.extend.configuration.storage.oss.aliyun;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.battcn.boot.extend.configuration.storage.AliYunStorageOperation;
import com.battcn.boot.extend.configuration.storage.domain.OssConstants;
import com.battcn.boot.extend.configuration.storage.endpoint.StorageEndpoint;
import com.battcn.boot.extend.configuration.storage.properties.AliYunStorageProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.battcn.boot.extend.configuration.storage.domain.OssConstants.MINIO_STORAGE_OPERATION;

/**
 * 阿里云 OSS
 *
 * @author Levin
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(AliYunStorageProperties.class)
@ConditionalOnProperty(prefix = OssConstants.OSS_CONFIG_PREFIX_ALIYUN, name = "enabled", havingValue = "true")
public class AliYunOssAutoConfiguration {


    @Bean
    public OSSClient ossClient(AliYunStorageProperties properties) {
        CredentialsProvider provider = new DefaultCredentialProvider(properties.getAccessKey(), properties.getSecretKey());
        return new OSSClient(properties.getEndpoint(), provider, new ClientConfiguration());
    }

    @Bean(MINIO_STORAGE_OPERATION)
    public AliYunStorageOperation aliYunStorageOperation(OSSClient ossClient, AliYunStorageProperties properties) {
        return new AliYunStorageOperation(ossClient, properties);
    }


    @Bean
    public StorageEndpoint aliYunOssEndpoint() {
        return new StorageEndpoint();
    }
}
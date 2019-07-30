package com.battcn.boot.request.configuration.storage.oss.minio;

import com.battcn.boot.request.configuration.storage.MinioStorageOperation;
import com.battcn.boot.request.configuration.storage.domain.OssConstants;
import com.battcn.boot.request.configuration.storage.properties.MinioStorageProperties;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static com.battcn.boot.request.configuration.storage.domain.OssConstants.ALI_YUN_STORAGE_OPERATION;


/**
 * @author Levin
 */
@Slf4j
@EnableConfigurationProperties(MinioStorageProperties.class)
@ConditionalOnProperty(prefix = OssConstants.OSS_CONFIG_PREFIX_MINIO, name = "enabled", havingValue = "true")
public class MinioOssAutoConfiguration {

    @Bean
    @SneakyThrows
    @ConditionalOnExpression("${request.storage.oss.minio.enabled:true}")
    public MinioClient minioClient(MinioStorageProperties properties) {
        MinioClient minioClient;
        try {
            minioClient = new MinioClient(
                    properties.getUrl(),
                    properties.getAccessKey(),
                    properties.getSecretKey(),
                    properties.isSecure()
            );
            minioClient.setTimeout(
                    properties.getConnectTimeout().toMillis(),
                    properties.getWriteTimeout().toMillis(),
                    properties.getReadTimeout().toMillis()
            );
        } catch (InvalidEndpointException | InvalidPortException e) {
            log.error("Error while connecting to Minio", e);
            throw e;
        }
        try {
            log.debug("Checking if bucket {} exists", properties.getBucket());
            boolean b = minioClient.bucketExists(properties.getBucket());
            if (!b) {
                throw new InvalidBucketNameException(properties.getBucket(), "Bucket does not exists");
            }
        } catch (InvalidBucketNameException | NoSuchAlgorithmException | InsufficientDataException | IOException | InvalidKeyException | NoResponseException | XmlPullParserException | ErrorResponseException | InternalException e) {
            log.error("Error while checking bucket", e);
            throw e;
        }
        return minioClient;
    }

    @Bean(ALI_YUN_STORAGE_OPERATION)
    public MinioStorageOperation minioStorageOperation(MinioClient minioClient, MinioStorageProperties properties) {
        return new MinioStorageOperation(minioClient, properties);
    }

}

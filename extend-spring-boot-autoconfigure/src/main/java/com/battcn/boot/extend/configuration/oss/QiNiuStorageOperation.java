package com.battcn.boot.extend.configuration.oss;

import com.battcn.boot.extend.configuration.oss.cloud.qiniu.QiNiuScope;
import com.battcn.boot.extend.configuration.oss.cloud.qiniu.connection.QiNiuConnectionFactory;
import com.battcn.boot.extend.configuration.oss.domain.StorageItem;
import com.battcn.boot.extend.configuration.oss.domain.StorageResponse;
import com.battcn.boot.extend.configuration.oss.properties.QiNiuStorageProperties;
import com.qiniu.cdn.CdnManager;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.util.List;


/**
 * @author Levin
 */
@Slf4j
public class QiNiuStorageOperation implements StorageOperation {

    private final UploadManager uploadManager;
    private final BucketManager bucketManager;
    private final CdnManager cdnManager;
    private final QiNiuStorageProperties properties;
    private final QiNiuConnectionFactory connectionFactory;

    @Autowired
    public QiNiuStorageOperation(QiNiuStorageProperties properties, QiNiuConnectionFactory connectionFactory) {
        this.properties = properties;
        this.connectionFactory = connectionFactory;
        this.uploadManager = this.connectionFactory.getUploadManager();
        this.bucketManager = this.connectionFactory.getBucketManager();
        this.cdnManager = this.connectionFactory.getCdnManager();
    }

    @SneakyThrows
    @Override
    public BufferedReader download(String fileName) {
        String domainOfBucket = this.connectionFactory.getDomain(properties.getBucket());
        String encodedFileName = URLEncoder.encode(fileName, "utf-8");
        String format = String.format("%s/%s", domainOfBucket, encodedFileName);
        return null;
    }

    @SneakyThrows
    @Override
    public BufferedReader download(String bucketName, String fileName) {
        return null;
    }

    @SneakyThrows
    @Override
    public void download(String bucketName, String fileName, File file) {

    }

    @SneakyThrows
    @Override
    public void download(String fileName, File file) {

    }

    @SneakyThrows
    @Override
    public List<StorageItem> list() {
        return null;
    }

    @SneakyThrows
    @Override
    public void rename(String oldName, String newName) {

    }

    @SneakyThrows
    @Override
    public void rename(String bucketName, String oldName, String newName) {

    }

    @SneakyThrows
    @Override
    public StorageResponse upload(String fileName, byte[] content) {
        return upload(properties.getBucket(), fileName, content);
    }

    @SneakyThrows
    @Override
    public StorageResponse upload(String bucketName, String fileName, InputStream content) {
        String upToken = getUploadToken(bucketName, fileName, QiNiuScope.DEFAULT);
        uploadManager.put(content, fileName, upToken, null, null);
        return StorageResponse.builder().build();
    }

    @SneakyThrows
    @Override
    public StorageResponse upload(String bucketName, String fileName, byte[] content) {
        String upToken = getUploadToken(bucketName, fileName, QiNiuScope.DEFAULT);
        uploadManager.put(content, fileName, upToken);
        return StorageResponse.builder().build();
    }

    @SneakyThrows
    @Override
    public void remove(String fileName) {
        remove(properties.getBucket(), fileName);
    }

    @SneakyThrows
    @Override
    public void remove(String bucketName, String fileName) {
        bucketManager.delete(bucketName, fileName);
    }

    @SneakyThrows
    @Override
    public void remove(String bucketName, Path path) {
        remove(bucketName, path.toString());
    }

    private String getUploadToken(String bucket, String key, QiNiuScope scope) {
        return this.connectionFactory.getUploadToken(bucket, key, scope);
    }
}

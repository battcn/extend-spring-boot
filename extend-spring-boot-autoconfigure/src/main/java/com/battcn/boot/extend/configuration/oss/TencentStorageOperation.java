package com.battcn.boot.extend.configuration.oss;

import com.battcn.boot.extend.configuration.oss.domain.StorageItem;
import com.battcn.boot.extend.configuration.oss.domain.StorageResponse;
import com.battcn.boot.extend.configuration.oss.exception.StorageException;
import com.battcn.boot.extend.configuration.oss.properties.BaseStorageProperties;
import com.battcn.boot.extend.configuration.oss.properties.TencentStorageProperties;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;


/**
 * @author Levin
 */
@Slf4j
@AllArgsConstructor
public class TencentStorageOperation implements StorageOperation {

    private final COSClient client;
    private final TencentStorageProperties properties;


    @Override
    public BufferedReader download(String fileName) {
        return null;
    }

    @Override
    public BufferedReader download(String bucketName, String fileName) {
        return null;
    }

    @Override
    public void download(String bucketName, String fileName, File file) {

    }

    @Override
    public void download(String fileName, File file) {

    }

    @Override
    public List<StorageItem> list() {
        return null;
    }

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
        //腾讯云必需要以"/"开头
        if (!fileName.startsWith(File.separator)) {
            fileName = File.separator + fileName;
        }
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(content.available());
        PutObjectRequest request = new PutObjectRequest(properties.getBucket(), fileName, content, objectMetadata);
        PutObjectResult result = client.putObject(request);
        if (StringUtils.isEmpty(result.getETag())) {
            throw new StorageException(BaseStorageProperties.StorageType.QCLOUD, "文件上传失败");
        }
        return StorageResponse.builder().storageItem(StorageItem
                .builder().path(properties.getMapperPath() + fileName)
                .name(fileName)
                .build()).build();
    }

    @SneakyThrows
    @Override
    public StorageResponse upload(String bucketName, String fileName, byte[] content) {
        //腾讯云必需要以"/"开头
        if (!fileName.startsWith(File.separator)) {
            fileName = File.separator + fileName;
        }
        ObjectMetadata objectMetadata = new ObjectMetadata();
        // 设置输入流长度为 500
        objectMetadata.setContentLength(content.length);
        PutObjectRequest request = new PutObjectRequest(properties.getBucket(), fileName, new ByteArrayInputStream(content), objectMetadata);
        PutObjectResult result = client.putObject(request);

        if (StringUtils.isEmpty(result.getETag())) {
            throw new StorageException(BaseStorageProperties.StorageType.QCLOUD, "文件上传失败");
        }
        return StorageResponse.builder().storageItem(StorageItem
                .builder().path(properties.getMapperPath() + fileName)
                .name(fileName)
                .build()).build();
    }

    @Override
    public void remove(String fileName) {

    }

    @Override
    public void remove(String bucketName, String fileName) {

    }

    @Override
    public void remove(String bucketName, Path path) {

    }
}

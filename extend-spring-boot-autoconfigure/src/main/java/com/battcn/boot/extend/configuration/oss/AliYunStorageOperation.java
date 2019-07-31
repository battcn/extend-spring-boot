package com.battcn.boot.extend.configuration.oss;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.battcn.boot.extend.configuration.oss.domain.StorageItem;
import com.battcn.boot.extend.configuration.oss.domain.StorageResponse;
import com.battcn.boot.extend.configuration.oss.properties.AliYunStorageProperties;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Levin
 */
@Slf4j
@AllArgsConstructor
public class AliYunStorageOperation implements StorageOperation {

    public static final AtomicLong FILE_UPLOAD_SUCCESS = new AtomicLong();
    public static final AtomicLong FILE_UPLOAD_FAIL = new AtomicLong();
    public static final AtomicLong FILE_GET_COUNTS = new AtomicLong();
    public static final AtomicLong FILE_DELETE_COUNTS = new AtomicLong();

    private final OSSClient ossClient;
    private final AliYunStorageProperties properties;

    @SneakyThrows
    @Override
    public BufferedReader download(String fileName) {
        return download(properties.getBucket(), fileName);
    }

    @SneakyThrows
    @Override
    public BufferedReader download(String bucketName, String fileName) {
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(bucketName, fileName);
        // 读取文件内容。
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
            }
            return reader;
        }
    }

    @SneakyThrows
    @Override
    public void download(String fileName, File file) {
        download(properties.getBucket(), fileName, file);
    }


    @SneakyThrows
    @Override
    public void download(String bucketName, String fileName, File file) {
        ossClient.getObject(new GetObjectRequest(bucketName, fileName), file);
    }


    @SneakyThrows
    @Override
    public List<StorageItem> list() {
        return null;
    }

    @SneakyThrows
    @Override
    public void rename(String oldName, String newName) {
        rename(properties.getBucket(), oldName, newName);
    }


    @SneakyThrows
    @Override
    public void rename(String bucketName, String oldName, String newName) {
        boolean keyExists = true;
        try {
            ossClient.getObjectMetadata(bucketName, oldName);
        } catch (Exception e) {
            keyExists = false;
        }
        if (keyExists) {
            ossClient.copyObject(bucketName, oldName, bucketName, newName);
        }
    }

    @SneakyThrows
    @Override
    public StorageResponse upload(String fileName, byte[] content) {
        return upload(properties.getBucket(), fileName, content);
    }

    @SneakyThrows
    @Override
    public StorageResponse upload(String bucketName, String fileName, InputStream content) {
        byte[] bytes = new byte[content.available()];
        return upload(properties.getBucket(), fileName, bytes);
    }

    /**
     * 上传文件到指定的 bucket
     *
     * @param bucketName 存储桶名称
     * @param fileName   文件名字
     * @param content    文件内容
     */
    @SneakyThrows
    @Override
    public StorageResponse upload(String bucketName, String fileName, byte[] content) {
        ByteArrayInputStream bis = new ByteArrayInputStream(content);
        try {
            ossClient.putObject(bucketName, fileName, bis);
            FILE_UPLOAD_SUCCESS.incrementAndGet();
        } catch (Exception ex) {
            ossClient.putObject(bucketName, fileName, bis);
            FILE_UPLOAD_FAIL.incrementAndGet();
            log.error("[异常信息]", ex);
        }
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
        ossClient.deleteObject(bucketName, fileName);
        FILE_DELETE_COUNTS.incrementAndGet();
    }

    @Override
    public void remove(String bucketName, Path path) {
        remove(bucketName, path.toString());
    }
}

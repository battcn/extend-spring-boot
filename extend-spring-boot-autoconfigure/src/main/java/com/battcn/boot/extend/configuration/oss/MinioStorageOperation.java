package com.battcn.boot.extend.configuration.oss;

import com.battcn.boot.extend.configuration.oss.domain.StorageItem;
import com.battcn.boot.extend.configuration.oss.domain.StorageResponse;
import com.battcn.boot.extend.configuration.oss.exception.StorageException;
import com.battcn.boot.extend.configuration.oss.properties.BaseStorageProperties;
import com.battcn.boot.extend.configuration.oss.properties.MinioStorageProperties;
import com.google.common.collect.Maps;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.entity.ContentType;
import org.xmlpull.v1.XmlPullParserException;

import java.io.*;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Levin
 */
@Slf4j
@AllArgsConstructor
public class MinioStorageOperation implements StorageOperation {


    private final MinioClient minioClient;
    private final MinioStorageProperties properties;

    @SneakyThrows
    @Override
    public BufferedReader download(String fileName) {
        return download(properties.getBucket(), fileName);
    }

    @SneakyThrows
    @Override
    public BufferedReader download(String bucketName, String fileName) {
        InputStream inputStream = minioClient.getObject(bucketName, fileName);
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    @SneakyThrows
    @Override
    public void download(String bucketName, String fileName, File file) {
        InputStream inputStream = minioClient.getObject(bucketName, fileName);
        OutputStream outputStream = new FileOutputStream(file);
        IOUtils.copy(inputStream, outputStream);
    }

    @SneakyThrows
    @Override
    public void download(String fileName, File file) {
        download(properties.getBucket(), fileName, file);
    }


    /**
     * 对象转换
     *
     * @param iterable 迭代内容
     * @return 返回结果
     */
    private List<StorageItem> getStorageItems(Iterable<Result<Item>> iterable) {
        return StreamSupport
                .stream(iterable.spliterator(), true)
                .map(itemResult -> {
                    try {
                        StorageItem storageItem = new StorageItem();
                        Item item = itemResult.get();
                        storageItem.setName(item.objectName());
                        storageItem.setSize(item.objectSize());
                        Map<String, Object> extended = Maps.newHashMap();
                        extended.put("tag", item.etag());
                        extended.put("storageClass", item.storageClass());
                        extended.put("lastModified", item.lastModified());
                        storageItem.setExtended(extended);
                        return storageItem;
                    } catch (InvalidBucketNameException |
                            NoSuchAlgorithmException |
                            InsufficientDataException |
                            IOException |
                            InvalidKeyException |
                            NoResponseException |
                            XmlPullParserException |
                            ErrorResponseException |
                            InternalException e) {
                        throw new StorageException("Error while parsing list of objects", e);
                    }
                })
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @Override
    public List<StorageItem> list() {
        Iterable<Result<Item>> iterable = minioClient.listObjects(properties.getBucket());
        return getStorageItems(iterable);
    }

    @SneakyThrows
    @Override
    public void rename(String oldName, String newName) {
        throw new StorageException(BaseStorageProperties.StorageType.MINIO, "方法未实现");
    }

    @SneakyThrows
    @Override
    public void rename(String bucketName, String oldName, String newName) {
        throw new StorageException(BaseStorageProperties.StorageType.MINIO, "方法未实现");
    }

    @SneakyThrows
    @Override
    public StorageResponse upload(String fileName, byte[] content) {
        InputStream stream = new ByteArrayInputStream(content);
        return upload(properties.getBucket(), fileName, stream);
    }

    @SneakyThrows
    @Override
    public StorageResponse upload(String bucketName, String fileName, InputStream content) {
        minioClient.putObject(bucketName, fileName, content, content.available(), ContentType.APPLICATION_OCTET_STREAM.getMimeType());
        return StorageResponse.builder()
                .successful(true)
                .storageItem(StorageItem.builder()
                        .name(fileName)
                        .path(properties.getMappingPath() + fileName)
                        .build())
                .build();
    }

    @SneakyThrows
    @Override
    public StorageResponse upload(String bucketName, String fileName, byte[] content) {
        return upload(bucketName, fileName, new ByteArrayInputStream(content));
    }

    @SneakyThrows
    @Override
    public void remove(String fileName) {
        remove(properties.getBucket(), fileName);
    }

    @SneakyThrows
    @Override
    public void remove(String bucketName, String fileName) {
        minioClient.removeObject(bucketName, fileName);
    }

    @SneakyThrows
    @Override
    public void remove(String bucketName, Path path) {
        remove(bucketName, path.toString());
    }
}

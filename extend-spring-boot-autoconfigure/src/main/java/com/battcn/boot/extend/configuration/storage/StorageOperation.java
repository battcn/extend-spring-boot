package com.battcn.boot.extend.configuration.storage;

import com.battcn.boot.extend.configuration.storage.domain.StorageItem;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

/**
 * 文件存储
 *
 * @author Levin
 */
public interface StorageOperation {

    /**
     * 文件下载（流式下载）
     *
     * @param fileName 文件名
     * @return BufferedReader BufferedReader
     */
    @SneakyThrows
    BufferedReader download(String fileName);

    /**
     * 文件下载（流式下载）
     *
     * @param bucketName 存储桶名
     * @param fileName   文件名
     * @return BufferedReader BufferedReader
     */
    @SneakyThrows
    BufferedReader download(String bucketName, String fileName);

    /**
     * 文件下载（文件下载到本地）
     *
     * @param bucketName 存储桶名
     * @param fileName   文件名
     * @param file       保存的本地文件路径
     */
    @SneakyThrows
    void download(String bucketName, String fileName, File file);

    /**
     * 文件下载（文件下载到本地）
     *
     * @param fileName 文件名
     * @param file     保存的本地文件路径
     */
    @SneakyThrows
    void download(String fileName, File file);

    /**
     * 文件列表
     *
     * @return 文件内容
     */
    @SneakyThrows
    List<StorageItem> list();

    /**
     * 重命名
     *
     * @param oldName 原始名称
     * @param newName 新名称
     */
    @SneakyThrows
    void rename(String oldName, String newName);

    /**
     * 重命名
     *
     * @param bucketName 存储桶名
     * @param oldName    原始名称
     * @param newName    新名称
     */
    @SneakyThrows
    void rename(String bucketName, String oldName, String newName);

    /**
     * 上传文件到指定的 bucket
     *
     * @param fileName 文件名字
     * @param content  文件内容
     */
    @SneakyThrows
    void upload(String fileName, byte[] content);

    /**
     * 上传文件到指定的 bucket
     *
     * @param bucketName 存储桶名
     * @param fileName   文件名字
     * @param content    文件内容
     */
    @SneakyThrows
    void upload(String bucketName, String fileName, InputStream content);

    /**
     * 上传文件到指定的 bucket
     *
     * @param bucketName 存储桶名
     * @param fileName   文件名字
     * @param content    文件内容
     */
    @SneakyThrows
    void upload(String bucketName, String fileName, byte[] content);

    /**
     * 删除文件
     *
     * @param fileName 文件名
     */
    @SneakyThrows
    void remove(String fileName);

    /**
     * 删除文件
     *
     * @param bucketName 存储桶名
     * @param fileName   文件名
     */
    @SneakyThrows
    void remove(String bucketName, String fileName);

    /**
     * 删除文件
     *
     * @param bucketName 存储桶名
     * @param path       文件路径
     */
    @SneakyThrows
    void remove(String bucketName, Path path);


}

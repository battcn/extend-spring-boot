package com.battcn.boot.request.configuration.storage.endpoint;

import com.battcn.boot.request.configuration.storage.AliYunStorageOperation;
import com.battcn.boot.request.configuration.storage.properties.AliYunStorageProperties;
import com.google.common.collect.Maps;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author <a href="mailto:1837307557@qq.com">Levin</a>
 * @since 2.0.2
 */
@Endpoint(id = "storage")
public class StorageEndpoint {

    @Resource
    private AliYunStorageProperties properties;


    @ReadOperation
    public Object invoke() {
        Map<String, Object> info = Maps.newLinkedHashMap();
        info.put("version", "2.2.1");
        info.put("vendor", "http://www.aliyun.com/");
        info.put("bucket", properties.getBucket());
        info.put("upload.success", AliYunStorageOperation.FILE_UPLOAD_SUCCESS.get());
        info.put("upload.fail", AliYunStorageOperation.FILE_UPLOAD_FAIL.get());
        info.put("get.count", AliYunStorageOperation.FILE_GET_COUNTS.get());
        info.put("delete.count", AliYunStorageOperation.FILE_DELETE_COUNTS.get());
        return info;
    }
}
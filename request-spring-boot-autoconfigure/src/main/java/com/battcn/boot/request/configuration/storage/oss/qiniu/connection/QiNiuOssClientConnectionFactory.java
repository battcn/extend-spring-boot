package com.battcn.boot.request.configuration.storage.oss.qiniu.connection;

import com.battcn.boot.request.configuration.storage.oss.qiniu.QiNiuScope;
import com.battcn.boot.request.configuration.storage.properties.QiNiuStorageProperties;
import com.qiniu.cdn.CdnManager;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.AllArgsConstructor;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Levin
 */
@AllArgsConstructor
public class QiNiuOssClientConnectionFactory implements QiNiuConnectionFactory {

    private final QiNiuStorageProperties qiNiuStorageProperties;


    @Override
    public Auth getAuth() {
        return Auth.create(qiNiuStorageProperties.getAccessKey(), qiNiuStorageProperties.getSecretKey());
    }

    private Configuration configuration() {
        Zone zone;
        switch (qiNiuStorageProperties.getZone()) {
            case zone0:
                zone = Zone.zone0();
                break;
            case zone1:
                zone = Zone.zone1();
                break;
            case zone2:
                zone = Zone.zone2();
                break;
            default:
                zone = Zone.zone0();
                break;
        }
        return new Configuration(zone);
    }

    @Override
    public BucketManager getBucketManager() {
        return new BucketManager(getAuth(), configuration());
    }

    @Override
    public UploadManager getUploadManager() {
        return new UploadManager(configuration());
    }

    @Override
    public String getUploadToken(String bucket, String key, QiNiuScope scope) {
        Map<String, QiNiuStorageProperties.QiNiuStrategy> strategies = qiNiuStorageProperties.getStrategies();
        QiNiuStorageProperties.QiNiuStrategy strategy = strategies.get(bucket);
        long expires = 3600L;
        boolean strict = qiNiuStorageProperties.isStrict();
        if (strategy == null) {
            // 生成上传凭证
            return getAuth().uploadToken(bucket, key, expires, null, strict);
        }
        StringMap policy = createPolicy(strategy);
        policy = applyScope(strategy, scope, bucket, key, policy);

        if (strategy.getDeadline() != null) {
            expires = strategy.getDeadline().getSeconds();
        }
        // 生成上传凭证
        return getAuth().uploadToken(bucket, key, expires, policy, strict);
    }

    @Override
    public String getUploadToken(String bucket, String key) {
        // 提供一个默认简单的上传凭证
        return getUploadToken(bucket, key, QiNiuScope.DEFAULT);
    }

    // 指定上传的目标资源空间 Bucket 和资源键 Key（最大为 750 字节）。有三种格式：
    // <bucket>，表示允许用户上传文件到指定的bucket。
    // 在这种格式下文件只能新增，若已存在同名资源（且文件内容/etag不一致），上传会失败；若已存在资源的内容/etag一致，则上传会返回成功。
    // <bucket>:<key>，表示只允许用户上传指定 key 的文件。在这种格式下文件默认允许修改，若已存在同名资源则会被覆盖。如果只希望上传指定
    // key 的文件，并且不允许修改，那么可以将下面的 insertOnly 属性值设为 1。
    // <bucket>:<keyPrefix>，表示只允许用户上传指定以 keyPrefix 为前缀的文件，当且仅当 isPrefixalScope
    // 字段为 1 时生效，isPrefixalScope 为 1 时无法覆盖上传。
    private StringMap applyScope(QiNiuStorageProperties.QiNiuStrategy strategy, QiNiuScope scope, String bucket, String key, StringMap policy) {
        if (QiNiuScope.REPLACE.equals(scope)) {
            policy.put("scope", bucket + ":" + key);
            policy.put("insertOnly", 0);
            policy.put("isPrefixScope", 0);
        } else if (QiNiuScope.PREFIXAL.equals(scope)) {
            policy.put("scope", bucket + ":" + strategy.getKeyPrefix());
            policy.put("isPrefixScope", 1);
        } else {
            policy.put("scope", bucket);
        }
        return policy;
    }

    private void clone(QiNiuStorageProperties.QiNiuStrategy source, QiNiuStorageProperties.QiNiuStrategy strategy) {
        BeanCopier copier = BeanCopier.create(QiNiuStorageProperties.QiNiuStrategy.class, QiNiuStorageProperties.QiNiuStrategy.class, true);
        copier.copy(source, strategy, (value, target, setterMethod) -> {
            String getterMethodName = ((String) setterMethod).replace("set", "get");
            if (value == null) {
                Method getterMethod = ReflectionUtils.findMethod(QiNiuStorageProperties.QiNiuStrategy.class, getterMethodName);
                ReflectionUtils.makeAccessible(getterMethod);
                return ReflectionUtils.invokeMethod(getterMethod, strategy);
            }
            return value;
        });
    }

    private StringMap createPolicy(QiNiuStorageProperties.QiNiuStrategy strategy) {
        // 针对不同的bucket提供不同的上传策略配置来覆盖默认配置
        StringMap policy = new StringMap();
        if (strategy.getPrefixScope() != null) {
            policy.put("prefixScope", strategy.getPrefixScope());
        }
        if (strategy.getInsertOnly() != null) {
            policy.put("insertOnly", strategy.getInsertOnly());
        }
        if (strategy.getEndUser() != null) {
            policy.put("endUser", strategy.getEndUser());
        }
        if (strategy.getReturnUrl() != null) {
            policy.put("returnUrl", strategy.getReturnUrl());
        }
        if (strategy.getReturnBody() != null) {
            policy.put("returnBody", strategy.getReturnBody());
        }
        if (strategy.getCallbackUrl() != null) {
            if (strategy.getCallbackBody() == null) {
                throw new IllegalStateException("Required callbackBody is not set");
            }
            policy.put("callbackUrl", strategy.getCallbackUrl());
        }
        if (strategy.getCallbackHost() != null) {
            policy.put("callbackHost", strategy.getCallbackHost());
        }
        if (strategy.getCallbackBody() != null) {
            policy.put("callbackBody", strategy.getCallbackBody());
        }
        if (strategy.getCallbackBodyType() != null) {
            policy.put("callbackBodyType", strategy.getCallbackBodyType());
        }
        if (strategy.getPersistentOps() != null) {
            policy.put("persistentOps", strategy.getPersistentOps());
        }
        if (strategy.getPersistentNotifyUrl() != null) {
            policy.put("persistentNotifyUrl", strategy.getPersistentNotifyUrl());
        }
        if (strategy.getPersistentPipeline() != null) {
            policy.put("persistentPipeline", strategy.getPersistentPipeline());
        }
        if (strategy.getSaveKey() != null) {
            policy.put("saveKey", strategy.getSaveKey());
        }
        if (strategy.getSizeMin() != null) {
            policy.put("sizeMin", strategy.getSizeMin());
        }
        if (strategy.getSizeMax() != null) {
            policy.put("sizeMax", strategy.getSizeMax());
        }
        if (strategy.getDetectMime() != null) {
            policy.put("detectMime", strategy.getDetectMime());
        }
        if (strategy.getMimeLimit() != null) {
            policy.put("mimeLimit", strategy.getMimeLimit());
        }
        if (strategy.getFileType() != null) {
            policy.put("fileType", strategy.getFileType());
        }
        return policy;
    }

    @Override
    public String getDomain(String bucket) {
        Map<String, QiNiuStorageProperties.QiNiuStrategy> strategies = qiNiuStorageProperties.getStrategies();
        // 获取配置中七牛空间的默认域名或者是绑定的自定义域名
        return strategies.get(bucket).getDomain();
    }

    @Override
    public CdnManager getCdnManager() {
        return new CdnManager(getAuth());
    }
}
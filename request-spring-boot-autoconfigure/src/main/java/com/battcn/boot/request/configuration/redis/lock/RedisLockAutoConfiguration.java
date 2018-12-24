package com.battcn.boot.request.configuration.redis.lock;

import com.battcn.boot.request.annotation.EnableRedisCacheLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Levin
 * @since 2018/12/24 0024
 */
@Slf4j
@ConditionalOnClass(RedisAutoConfiguration.class)
public class RedisLockAutoConfiguration implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableRedisCacheLock.class.getName()));
        final boolean lockInterceptor = annotationAttributes.getBoolean("lockInterceptor");
        final boolean defaultKeyGenerator = annotationAttributes.getBoolean("defaultKeyGenerator");
        GenericBeanDefinition redisLockDefinition = new GenericBeanDefinition();
        redisLockDefinition.setBeanClass(RedisLockTemplate.class);
        redisLockDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
        registry.registerBeanDefinition("redisLockTemplate", redisLockDefinition);
        if (lockInterceptor) {
            GenericBeanDefinition definition = new GenericBeanDefinition();
            definition.setBeanClass(RedisLockInterceptor.class);
            definition.setScope(BeanDefinition.SCOPE_SINGLETON);
            registry.registerBeanDefinition("redisLockInterceptor", definition);
        }
        if (defaultKeyGenerator) {
            GenericBeanDefinition definition = new GenericBeanDefinition();
            definition.setBeanClass(DefaultRedisLockKeyGenerator.class);
            definition.setScope(BeanDefinition.SCOPE_SINGLETON);
            registry.registerBeanDefinition("defaultKeyGenerator", definition);
        }
    }
}

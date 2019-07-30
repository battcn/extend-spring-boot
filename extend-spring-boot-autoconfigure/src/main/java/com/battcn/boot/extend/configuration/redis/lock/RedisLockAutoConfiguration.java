package com.battcn.boot.extend.configuration.redis.lock;

import com.battcn.boot.extend.configuration.redis.annotation.EnableRedisLock;
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
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableRedisLock.class.getName()));
        final boolean interceptor = annotationAttributes.getBoolean("interceptor");
        GenericBeanDefinition redisLockDefinition = new GenericBeanDefinition();
        redisLockDefinition.setBeanClass(RedisLockHelper.class);
        redisLockDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
        registry.registerBeanDefinition("redisLockHelper", redisLockDefinition);
        if (interceptor) {
            GenericBeanDefinition definition = new GenericBeanDefinition();
            definition.setBeanClass(RedisLockInterceptor.class);
            definition.setScope(BeanDefinition.SCOPE_SINGLETON);
            registry.registerBeanDefinition("redisLockInterceptor", definition);
        }
    }
}

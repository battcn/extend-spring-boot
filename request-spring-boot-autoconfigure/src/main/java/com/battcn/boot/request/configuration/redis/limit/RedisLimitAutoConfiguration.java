package com.battcn.boot.request.configuration.redis.limit;

import com.battcn.boot.request.annotation.EnableRedisLimit;
import com.battcn.boot.request.configuration.redis.DefaultRedisKeyGenerator;
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
public class RedisLimitAutoConfiguration implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableRedisLimit.class.getName()));
        final boolean interceptor = annotationAttributes.getBoolean("interceptor");
        final boolean defaultKeyGenerator = annotationAttributes.getBoolean("defaultKeyGenerator");
        GenericBeanDefinition redisLimitDefinition = new GenericBeanDefinition();
        redisLimitDefinition.setBeanClass(RedisLimitHelper.class);
        redisLimitDefinition.setScope(BeanDefinition.SCOPE_SINGLETON);
        registry.registerBeanDefinition("redisLimitHelper", redisLimitDefinition);
        if (interceptor) {
            GenericBeanDefinition definition = new GenericBeanDefinition();
            definition.setBeanClass(RedisLimitInterceptor.class);
            definition.setScope(BeanDefinition.SCOPE_SINGLETON);
            registry.registerBeanDefinition("redisLimitInterceptor", definition);
        }
        final String defaultKeyName = "defaultKeyGenerator";
        if (defaultKeyGenerator && !registry.isBeanNameInUse(defaultKeyName)) {
            GenericBeanDefinition definition = new GenericBeanDefinition();
            definition.setBeanClass(DefaultRedisKeyGenerator.class);
            definition.setPrimary(false);
            definition.setScope(BeanDefinition.SCOPE_SINGLETON);
            registry.registerBeanDefinition(defaultKeyName, definition);
        }
    }
}
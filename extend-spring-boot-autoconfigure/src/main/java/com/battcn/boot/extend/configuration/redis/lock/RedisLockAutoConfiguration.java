package com.battcn.boot.extend.configuration.redis.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.StringRedisTemplate;

import static com.battcn.boot.extend.configuration.commons.ExtendBeanTemplate.*;

/**
 * @author Levin
 * @since 2018/12/24 0024
 */
@Slf4j
@ConditionalOnClass(RedisAutoConfiguration.class)
@Import(RedisLockInterceptor.class)
@ConditionalOnProperty(prefix = REDIS_LOCK, name = ENABLED, havingValue = TRUE, matchIfMissing = true)
public class RedisLockAutoConfiguration {

    @Bean(REDIS_LOCK_HELPER)
    public RedisLockHelper redisLockHelper(StringRedisTemplate stringRedisTemplate) {
        return new RedisLockHelper(stringRedisTemplate);
    }


}

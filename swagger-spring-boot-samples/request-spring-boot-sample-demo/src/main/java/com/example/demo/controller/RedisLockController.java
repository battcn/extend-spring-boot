package com.example.demo.controller;

import com.battcn.boot.request.configuration.redis.lock.annotation.LockParam;
import com.battcn.boot.request.configuration.redis.lock.annotation.RedisLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Levin
 * @since 2018/12/24 0024
 */
@RestController
@RequestMapping("/locks")
public class RedisLockController {


    @GetMapping
    @RedisLock(prefix = "test", timeUnit = TimeUnit.DAYS, message = "被我拿到锁啦")
    public String test1(@LockParam(name = "a") String a, @LockParam String b) {
        return a + b;
    }


}

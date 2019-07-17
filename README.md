# 简介

`request-spring-boot-starter` 对是一个 `request/response` 的增强包，每个功能都是通过注解形式开启，不会有任何额外的影响开销
比如只想用日期格式化 `@EnableLocalDateTimeFormat` 即可，其它无关的 `Bean` 就不会被初始化，不会造成资源浪费 


> 特点

- 使 `spring.jackson.date-format` 属性支持 `JDK8` 日期格式化
- 解决 `request.getInputStream()` 一次读取后失效痛点  
- 国际化支持
- 全局跨域支持
- 接口加密/解密
- 防XSS攻击
- 分布式限流/分布式锁支持

# 使用方法

``` xml
<dependency>
    <groupId>com.battcn</groupId>
    <artifactId>request-spring-boot-starter</artifactId>
    <version>1.0.9-SNAPSHOT</version>
</dependency>
```

> 注意事项

**如果你想使用 `EnableRedis` 相关组件，你需要额外依赖**

``` xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

**如果你想使用 `EnableRequestWrapperFilter` 相关组件，你需要额外依赖**

``` xml
 <dependency>
    <groupId>commons-io</groupId>
    <artifactId>commons-io</artifactId>
    <version>2.6</version>
</dependency>
```

**如果你想使用 `EnableXssFilter` 相关组件，你需要额外依赖**

``` xml
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-text</artifactId>
    <version>1.6</version>
</dependency>
```



在主函数（Application.java） 中添加相应注解即可

``` java
import com.battcn.boot.request.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Levin
 */
@EnableI18n
@EnableCorsFilter
@EnableLocalDateTimeFormat
@EnableRequestWrapperFilter
@EnableRedisLock
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

```


## 注解介绍

> @EnableXssFilter

添加对 XSS 攻击转义的支持

> @EnableRedisLimit

添加分布式限流的支持，在启动类上加上该注解即可，然后在请求的接口上面加上`@RedisLimit`

> @EnableRedisLock

添加分布式锁的支持，在启动类上加上该注解即可，然后在请求的接口上面加上`@RedisLock`

> @EnableI18n

`I18N` 国际化支持
 
> @EnableCorsFilter

全局跨域支持

> EnableRequestWrapperFilter

继承 `HttpServletRequestWrapper` 实现`BodyCacheHttpServletRequestWrapper`，解决 `request.getInputStream()` 一次读取后失效痛点

> EnableLocalDateTimeFormat

使 `spring.jackson.date-format` 属性支持 `JDK8` 日期格式化
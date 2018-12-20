# 简介

`request-spring-boot-starter` 对是一个 `request/response` 的增强包，每个功能都是通过注解形式开启，不会有任何额外的影响开销
比如只想用日期格式化 `@EnableLocalDateTimeFormat` 即可，其它无关的 `Bean` 就不会被初始化，不会造成资源浪费 


> 特点

- 使 `spring.jackson.date-format` 属性支持 `JDK8` 日期格式化
- 解决 `request.getInputStream()` 一次读取后失效痛点  
- 国际化支持
- 全局跨域支持

# 使用方法

``` xml
<dependency>
    <groupId>com.battcn</groupId>
    <artifactId>request-spring-boot-starter</artifactId>
    <version>1.0.3-RELEASE</version>
</dependency>
```

在主函数（Application.java） 中添加相应注解即可

``` java
import com.battcn.boot.request.annotation.EnableLocalDateTimeFormat;
import com.battcn.boot.request.annotation.EnableRequestWrapperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Levin
 */
@EnableI18n
@EnableCorsFilter
@EnableLocalDateTimeFormat
@EnableRequestWrapperFilter
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```


## 注解介绍

> @EnableI18n

`I18N` 国际化支持
 
> @EnableCorsFilter

全局跨域支持

> EnableRequestWrapperFilter

继承 `HttpServletRequestWrapper` 实现`BodyCacheHttpServletRequestWrapper`，解决 `request.getInputStream()` 一次读取后失效痛点

> EnableLocalDateTimeFormat

使 `spring.jackson.date-format` 属性支持 `JDK8` 日期格式化
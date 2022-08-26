package com.atguigu.gmall.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * date 2022/8/26 22:42
 * @message 因为这个服务我们用的后端技术Thymeleaf来解析页面等，没有专门的前端技术，所以它本身也是一块微服务
 * 这个微服务不需要操作数据库，所以不要启用数据源的自动配置
 * 方法一： 排除jar包
 * 方法二：使用注解排除数据源配置(下面提供两种方式)
 * @author 孙永刚
*/
@SpringCloudApplication//（采用配置文件方式）
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@EnableDiscoveryClient
//@EnableCircuitBreaker
public class WebMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebMainApplication.class,args);
    }
}

package com.atguigu.gmall.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * 不要启用数据源的自动配置
 * 1、DataSourceAutoConfiguration 就会生效
 *
 * 前端项目-页面跳转与数据渲染（thymeleaf）
 *
 */
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@EnableDiscoveryClient
//@EnableCircuitBreaker
//因为com.atguigu.gmall.feign.product下有两个远程调用接口，所以会报错，需要在配置文件中spring.main.allow-bean-definition-overriding=true
@EnableFeignClients(basePackages = {
        "com.atguigu.gmall.feign.item",
        "com.atguigu.gmall.feign.product"
}) //只会扫描主程序所在的子包
@SpringCloudApplication
public class WebAllMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebAllMainApplication.class,args);
    }
}

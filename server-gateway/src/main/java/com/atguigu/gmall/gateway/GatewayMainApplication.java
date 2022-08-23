package com.atguigu.gmall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EnableDiscoveryClient//开启服务发现（1.导入服务发现jar 2.使用这个注解）
//@EnableCircuitBreaker//开启服务熔断降级，流量保护（1.导入jar 2使用这个注解）
//@SpringBootApplication
@SpringCloudApplication//代替以上三个
public class GatewayMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMainApplication.class,args);
    }
}

package com.atguigu.gmall.item;


import com.atguigu.gmall.common.config.annotation.EnableThreadPool;
import com.atguigu.gmall.common.config.threadpool.AppThreadPoolAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@EnableFeignClients
@SpringCloudApplication
//@Import(AppThreadPoolAutoConfiguration.class)
@EnableThreadPool
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class,args);
    }
}

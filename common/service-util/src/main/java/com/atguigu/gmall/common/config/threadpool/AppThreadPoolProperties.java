package com.atguigu.gmall.common.config.threadpool;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.thread-pool")
@Data

public class AppThreadPoolProperties {

    Integer core ;
    Integer max ;
    Integer queueSize ;
    Long keepAliveTime ;


}

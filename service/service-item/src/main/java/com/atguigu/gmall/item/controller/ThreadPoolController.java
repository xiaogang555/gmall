package com.atguigu.gmall.item.controller;


import com.atguigu.gmall.common.result.Result;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
public class ThreadPoolController {

    @Resource
    ThreadPoolExecutor executor;

    @GetMapping("/close/pool")
    public Result closePool(){
        executor.shutdown(); //关闭线程池
        return Result.ok();
    }

    @GetMapping("/monitor/pool")
    public Result monitorThreadPool(){

        int poolSize = executor.getCorePoolSize();
        long taskCount = executor.getTaskCount();

        return Result.ok(poolSize + "====" + taskCount);
    }
}

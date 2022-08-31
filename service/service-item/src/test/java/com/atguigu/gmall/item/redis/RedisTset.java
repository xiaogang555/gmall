package com.atguigu.gmall.item.redis;


import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTset {


    @Resource
    StringRedisTemplate stringRedisTemplate;


    @Test
    public void testSaveRedis(){

        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        stringStringValueOperations.set("ggu","guhahah");
        System.out.println("保存完毕");

        String ggu = stringStringValueOperations.get("ggu");
        System.out.println(ggu);

    }
}

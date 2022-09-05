package com.atguigu.gmall.product;



import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.mapper.BaseTrademarkMapper;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ShardingTest {



    @Resource
    BaseTrademarkMapper baseTrademarkMapper;

    @Test
    public void test(){


        BaseTrademark trademark = baseTrademarkMapper.selectById(4L);
        System.out.println(trademark);
    }
}

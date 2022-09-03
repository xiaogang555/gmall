package com.atguigu.gmall.product.controller;


import com.atguigu.gmall.common.constant.SysRedisConst;
import com.atguigu.gmall.common.result.Result;

import com.atguigu.gmall.product.bloom.BloomDataQueryService;
import com.atguigu.gmall.product.bloom.BloomOpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * date 2022/9/3 18:37
 * @message 布隆过滤器操作
 * @author 孙永刚
*/
@RestController
@RequestMapping("/admin/product")
public class BloomOpsController {

    @Autowired
    BloomOpsService bloomOpsService;
    @Autowired
    BloomDataQueryService bloomDataQueryService;
    @RequestMapping(value = "/rebuild/sku/now",method = RequestMethod.GET)
    public Result rebuildBloom(){
        String bloomName= SysRedisConst.BLOOM_SKUID;
        bloomOpsService.rebuildBloom(bloomName,bloomDataQueryService);
    return Result.ok();
    }
}

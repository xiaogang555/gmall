package com.atguigu.gmall.item.service;

import com.atguigu.gmall.model.to.SkuDetailTo;

public interface SkuDetailService {
    SkuDetailTo getSkuDetail(Long skuId);


    //未进行缓存优化
    SkuDetailTo getSkuDetailFromRpc(Long skuId);

}

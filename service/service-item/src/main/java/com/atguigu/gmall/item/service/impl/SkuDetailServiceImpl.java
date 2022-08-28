package com.atguigu.gmall.item.service.impl;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.item.feign.SkuDetailFeignClient;
import com.atguigu.gmall.item.service.SkuDetailService;
import com.atguigu.gmall.model.to.SkuDetailTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkuDetailServiceImpl  implements SkuDetailService {
    @Autowired
    SkuDetailFeignClient skuDetailFeignClient;

    @Override
    public SkuDetailTo getSkuDetail(Long skuId) {

        SkuDetailTo skuDetailTo = new SkuDetailTo();

        Result<SkuDetailTo> skuDetail = skuDetailFeignClient.getSkuDetail(skuId);

        //远程查询商品详情
        //包含以下内容：1.sku所属分类完整信息




        // 2. sku基本信息
        // 3.sku图片信息
        // 4.sku所属spu当时定义的所有的销售属性名和值组合【标识出当前sku到底是spu的那种组合，页面要高亮显示】
        //5. sku类似推荐········未做
        //6. sku详情介绍
        //7. sku规格参数
        //8. sku售后 评论··········未做
        return skuDetail.getData();
    }
}

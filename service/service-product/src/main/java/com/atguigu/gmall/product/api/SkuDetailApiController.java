package com.atguigu.gmall.product.api;


import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.product.service.SkuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


//商品详细数据库层操作
@RestController
@RequestMapping("/api/inner/rpc/product")//命名规范
public class SkuDetailApiController {

    @Autowired
    SkuInfoService skuInfoService;
    
    @RequestMapping(value = "/skudetail/{skuId}",method = RequestMethod.GET)
    public Result<SkuDetailTo> getSkuDetail(@PathVariable Long skuId){

        SkuDetailTo skuDetailTo= skuInfoService.getSkuDetail(skuId);


        //包含以下内容：1.sku所属分类完整信息
        // 2. sku基本信息
        // 3.sku图片信息
        // 4.sku所属spu当时定义的所有的销售属性名和值组合【标识出当前sku到底是spu的那种组合，页面要高亮显示】
        //5. sku类似推荐········未做
        //6. sku详情介绍
        //7. sku规格参数
        //8. sku售后 评论··········未做
        
    return Result.ok(skuDetailTo);
    }

}

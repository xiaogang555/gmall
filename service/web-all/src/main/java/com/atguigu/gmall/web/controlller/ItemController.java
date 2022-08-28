package com.atguigu.gmall.web.controlller;


import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.web.feign.SkuDetailFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemController {

    /**
     * date 2022/8/27 20:42
     * @message 商品详情页
     * @author 孙永刚
    */

    @Autowired
    SkuDetailFeignClient skuDetailFeignClient;

    @GetMapping("/{skuId}.html")
    public String itemPage(@PathVariable Long skuId,Model model){


        //远程查询商品详情
        //包含以下内容：1.sku所属分类完整信息
        // 2. sku基本信息
        // 3.sku图片信息
        // 4.sku所属spu当时定义的所有的销售属性名和值组合【标识出当前sku到底是spu的那种组合，页面要高亮显示】
        //5. sku类似推荐········未做
        //6. sku详情介绍
        //7. sku规格参数
        //8. sku售后 评论··········未做

        Result<SkuDetailTo> result = skuDetailFeignClient.getSkuDetail(skuId);
        if (result.isOk()){
            SkuDetailTo skuDetailTo = result.getData();
            model.addAttribute("categoryView",skuDetailTo.getCategoryView());

            model.addAttribute("skuInfo",skuDetailTo.getSkuInfo());
            model.addAttribute("price",skuDetailTo.getPrice());
            model.addAttribute("spuSaleAttrList",skuDetailTo.getSpuSaleAttrList());
            model.addAttribute("valuesSkuJson",skuDetailTo.getValuesSkuJson());
        }

        return "item/index";
    }

}

package com.atguigu.gmall.product.controller;


import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.service.SkuInfoService;
import com.atguigu.gmall.product.service.SpuImageService;
import com.atguigu.gmall.product.service.SpuInfoService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class SkuController {
    @Autowired
    SpuImageService spuImageService;

    @Autowired
    SkuInfoService skuInfoService;


    @RequestMapping(value = "/spuImageList/{spuId}",method = RequestMethod.GET)///spuImageList/{spuId}
    public Result getSpuImageList(@PathVariable Long spuId){

        LambdaQueryWrapper<SpuImage> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<SpuImage> wrapper = queryWrapper.eq(SpuImage::getSpuId, spuId);
        List<SpuImage> list = spuImageService.list(wrapper);


        return Result.ok(list);
    }
    @RequestMapping(value = "/saveSkuInfo",method =RequestMethod.POST) //saveSkuInfo
    public Result saveSkuInfo(@RequestBody SkuInfo skuInfo){

        skuInfoService.saveSkuInfo(skuInfo);
    return Result.ok();
    }
    @RequestMapping(value = "/list/{pagenum}/{pagesize}",method =RequestMethod.GET)//list/{page}/{limit}
    public Result getSkuPageList(@PathVariable Long pagenum,
                                 @PathVariable Long pagesize){

        IPage<SkuInfo> page=new Page<>(pagenum,pagesize);

        IPage<SkuInfo> result = skuInfoService.page(page);
        return Result.ok(result);
    }




    @RequestMapping(value = "/onSale/{skuId}",method =RequestMethod.GET)
    public Result cancelSale(@PathVariable("skuId")Long skuId){
        skuInfoService.cancelSale(skuId);
        return Result.ok();
    }

    @RequestMapping(value = "/cancelSale/{skuId}",method =RequestMethod.GET)
    public Result onSale(@PathVariable("skuId")Long skuId){
        skuInfoService.onSale(skuId);
        return Result.ok();
    }


}

package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.atguigu.gmall.common.result.Result;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/product")///admin/product/{page}/{limit}?category3Id=61
public class SpuController {
    @Autowired
    SpuInfoService spuInfoService;

    @Autowired
    BaseSaleAttrService baseSaleAttrService;

    @Autowired
    BaseTrademarkService baseTrademarkService;

    @Autowired
    SpuSaleAttrService spuSaleAttrService;



    @RequestMapping(value = "/{pagenum}/{pagesize}",method = RequestMethod.GET)
    public Result getSupPage(@PathVariable Long pagenum,
                             @PathVariable Long pagesize,
                             @RequestParam Long category3Id){

        IPage<SpuInfo> page=new Page<>(pagenum,pagesize);
        LambdaQueryWrapper<SpuInfo> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<SpuInfo> wrapper = queryWrapper.eq(SpuInfo::getCategory3Id, category3Id);

        IPage<SpuInfo> pagelist = spuInfoService.page(page, wrapper);

        return Result.ok(pagelist);
    }
    
    @RequestMapping(value = "/baseSaleAttrList",method =RequestMethod.GET)//baseSaleAttrList
    public Result getbaseSaleAttrList(){
        List<BaseSaleAttr> list = baseSaleAttrService.list();

        return Result.ok(list);
    }
    


    @RequestMapping(value = "/baseTrademark/getTrademarkList",method =RequestMethod.GET)///baseTrademark/getTrademarkList
    public Result getBaseTrademark(){
        List<BaseTrademark> list = baseTrademarkService.list();
        return Result.ok(list);
    }

    @RequestMapping(value = "/saveSpuInfo",method =RequestMethod.POST)///saveSpuInfo
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo){

        spuInfoService.saveSpuInfo(spuInfo);
    return Result.ok();
    }


    @RequestMapping(value = "/spuSaleAttrList/{spuId}",method =RequestMethod.GET) //spuSaleAttrList/{spuId}
    public Result getSpuSaleAttrList(@PathVariable Long spuId){


        List<SpuSaleAttr> list = spuSaleAttrService.getSaleAttrAndValueBySpuId(spuId);




        return Result.ok(list);
    }

    
}

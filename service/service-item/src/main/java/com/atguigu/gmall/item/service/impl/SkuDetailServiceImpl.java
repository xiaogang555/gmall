package com.atguigu.gmall.item.service.impl;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.item.feign.SkuDetailFeignClient;
import com.atguigu.gmall.item.service.SkuDetailService;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class SkuDetailServiceImpl  implements SkuDetailService {
    @Autowired
    SkuDetailFeignClient skuDetailFeignClient;

    /**
     * 可配置的线程池，可自动注入
     */
    @Autowired
    ThreadPoolExecutor executor;

    @Override
    public SkuDetailTo getSkuDetail(Long skuId) {

        SkuDetailTo skuDetailTo = new SkuDetailTo();



//        Result<SkuDetailTo> skuDetail = skuDetailFeignClient.getSkuDetail(skuId); 总方法

//        //1、查基本信息
//        Result<SkuInfo> result = skuDetailFeignClient.getSkuInfo(skuId);
//        SkuInfo skuInfo = result.getData();
//        skuDetailTo.setSkuInfo(skuInfo);
//
//        //2、查商品图片信息
//        Result<List<SkuImage>> skuImages = skuDetailFeignClient.getSkuImages(skuId);
//        skuInfo.setSkuImageList(skuImages.getData());
//        //3、查商品实时价格
//        Result<BigDecimal> price = skuDetailFeignClient.getSku1010Price(skuId);
//        skuDetailTo.setPrice(price.getData());
//        //4、查销售属性名值
//        Result<List<SpuSaleAttr>> saleattrvalues = skuDetailFeignClient.getSkuSaleattrvalues(skuId, skuInfo.getSpuId());
//        skuDetailTo.setSpuSaleAttrList(saleattrvalues.getData());
//        //5、查sku组合
//        Result<String> sKuValueJson = skuDetailFeignClient.getSKuValueJson(skuInfo.getSpuId());
//        skuDetailTo.setValuesSkuJson(sKuValueJson.getData());
//
//        //6、查分类
//        Result<CategoryViewTo> categoryView = skuDetailFeignClient.getCategoryView(skuInfo.getCategory3Id());
//        skuDetailTo.setCategoryView(categoryView.getData());


        //1、查基本信息   1s
        CompletableFuture<SkuInfo> skuInfoFuture = CompletableFuture.supplyAsync(() -> {
            Result<SkuInfo> result = skuDetailFeignClient.getSkuInfo(skuId);
            SkuInfo skuInfo = result.getData();
            skuDetailTo.setSkuInfo(skuInfo);
            return skuInfo;
        }, executor);


        //2、查商品图片信息  1s
        CompletableFuture<Void> imageFuture = skuInfoFuture.thenAcceptAsync(skuInfo -> {
            Result<List<SkuImage>> skuImages = skuDetailFeignClient.getSkuImages(skuId);
            skuInfo.setSkuImageList(skuImages.getData());
        }, executor);




        //3、查商品实时价格 2s
        CompletableFuture<Void> priceFuture = CompletableFuture.runAsync(() -> {
            Result<BigDecimal> price = skuDetailFeignClient.getSku1010Price(skuId);
            skuDetailTo.setPrice(price.getData());
        }, executor);


        //4、查销售属性名值
        CompletableFuture<Void> saleAttrFuture = skuInfoFuture.thenAcceptAsync(skuInfo -> {
            Long spuId = skuInfo.getSpuId();
            Result<List<SpuSaleAttr>> saleattrvalues = skuDetailFeignClient.getSkuSaleattrvalues(skuId, spuId);
            skuDetailTo.setSpuSaleAttrList(saleattrvalues.getData());
        }, executor);


        //5、查sku组合
        CompletableFuture<Void> skuVlaueFuture = skuInfoFuture.thenAcceptAsync(skuInfo -> {
            Result<String> sKuValueJson = skuDetailFeignClient.getSKuValueJson(skuInfo.getSpuId());
            skuDetailTo.setValuesSkuJson(sKuValueJson.getData());
        }, executor);



        //6、查分类
        CompletableFuture<Void> categoryFuture = skuInfoFuture.thenAcceptAsync(skuInfo -> {
            Result<CategoryViewTo> categoryView = skuDetailFeignClient.getCategoryView(skuInfo.getCategory3Id());
            skuDetailTo.setCategoryView(categoryView.getData());
        },executor);


        CompletableFuture
                .allOf(imageFuture,priceFuture,saleAttrFuture,skuVlaueFuture,categoryFuture)
                .join();

        return skuDetailTo;
    }
}

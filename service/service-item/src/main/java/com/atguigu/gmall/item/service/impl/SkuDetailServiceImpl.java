package com.atguigu.gmall.item.service.impl;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.common.util.Jsons;
import com.atguigu.gmall.item.feign.SkuDetailFeignClient;
import com.atguigu.gmall.item.service.SkuDetailService;
import com.atguigu.gmall.model.product.SkuImage;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class SkuDetailServiceImpl  implements SkuDetailService {
    @Autowired
    SkuDetailFeignClient skuDetailFeignClient;

    /**
     * 可配置的线程池，可自动注入
     */
    @Autowired
    ThreadPoolExecutor executor;


    @Resource
    StringRedisTemplate stringRedisTemplate;


    /*
    * map（本地缓存）作为缓存优缺点
    * 缺点：容量有限///数据同步问题
    *
    * 建议:大量数据的缓存不要用本地缓存  采用分布式缓存
    *
    * */
//    private Map<Long,SkuDetailTo> skuCache=new ConcurrentHashMap<>();




    //未进行缓存优化前
    @Override
    public SkuDetailTo getSkuDetailFromRpc(Long skuId) {

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


//    //进行缓存优化（使用本地缓存）
//    @Override
//    public SkuDetailTo getSkuDetail(Long skuId) {
//        //1.先看缓存
//        SkuDetailTo cacheDate = skuCache.get(skuId);
//
//        //2.判断
//        //如果没有，真正查询【回源】
//        if (cacheDate==null){
//            //1 - 0/1 0%
//            //2 - 1/2 50%
//            //n - n-1/n ≈100%
//            //缓存名字率提升到100% ： 预缓存机制
//
//            SkuDetailTo fromRpc = getSkuDetailFromRpc(skuId);
//            skuCache.put(skuId,fromRpc);
//            return  fromRpc;
//        }
//        //3.缓存有
//        return cacheDate;
//    }



    //使用redis缓存
    @Override
    public SkuDetailTo getSkuDetail(Long skuId) {
        //1. 查看缓存中有没有 sku:info:50
        String jsonString = stringRedisTemplate.opsForValue().get("sku:info:"+skuId);

        if ("x".equals(jsonString)){
            //说明以前查过，只不过数据库没有此纪录，为了避免再次回源，缓存了一个占位符
            return  null;
        }

        if (StringUtils.isEmpty(jsonString)){
            //redis没有缓存数据
            //回源   回源之前判断redis中保存的sku的id的集合，有没有这个id
            //防止随机值穿透攻击?
            SkuDetailTo fromRpc = getSkuDetailFromRpc(skuId);
            //放入缓存

            String cacheJson="x";
            if (fromRpc!=null){
                cacheJson = Jsons.toString(fromRpc);
                stringRedisTemplate.opsForValue().set("sku:info:"+skuId, cacheJson,7, TimeUnit.DAYS);
            }else {
                stringRedisTemplate.opsForValue().set("sku:info:"+skuId, cacheJson,7, TimeUnit.DAYS);
            }

            return  fromRpc;

        }

        //缓存中有//把json转为对象
       SkuDetailTo skuDetailTo= Jsons.toObj(jsonString,SkuDetailTo.class);
        return skuDetailTo;
    }
}

package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.atguigu.gmall.model.to.SkuDetailTo;
import com.atguigu.gmall.model.to.ValueSkuJsonTo;
import com.atguigu.gmall.product.mapper.BaseCategory3Mapper;
import com.atguigu.gmall.product.mapper.SpuSaleAttrMapper;
import com.atguigu.gmall.product.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.atguigu.gmall.product.mapper.SkuInfoMapper;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
* @author 孙永刚
* @description 针对表【sku_info(库存单元表)】的数据库操作Service实现
* @createDate 2022-08-24 16:43:06
*/
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo>
    implements SkuInfoService{

    @Autowired
    SkuImageService skuImageService;

    @Autowired
    SkuAttrValueService skuAttrValueService;

    @Autowired
    SkuSaleAttrValueService skuSaleAttrValueService;

    @Resource
    SkuInfoMapper skuInfoMapper;

    @Resource
    BaseCategory3Mapper baseCategory3Mapper;

    @Resource
    SpuSaleAttrService spuSaleAttrService;


    @Resource
    SpuSaleAttrMapper spuSaleAttrMapper;

    @Transactional
    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        //1、sku基本信息保存到 sku_info
        save(skuInfo);
        Long skuId = skuInfo.getId();

        //2、sku的图片信息保存到 sku_image
        for (SkuImage skuImage : skuInfo.getSkuImageList()) {
            skuImage.setSkuId(skuId);
        }
        skuImageService.saveBatch(skuInfo.getSkuImageList());

        //3、sku的平台属性名和值的关系保存到 sku_attr_value
        List<SkuAttrValue> attrValueList = skuInfo.getSkuAttrValueList();
        for (SkuAttrValue attrValue : attrValueList) {
            attrValue.setSkuId(skuId);
        }
        skuAttrValueService.saveBatch(attrValueList);

        //4、sku的销售属性名和值的关系保存到 sku_sale_attr_value
        List<SkuSaleAttrValue> saleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        for (SkuSaleAttrValue saleAttrValue : saleAttrValueList) {
            saleAttrValue.setSkuId(skuId);
            saleAttrValue.setSpuId(skuInfo.getSpuId());
        }
        skuSaleAttrValueService.saveBatch(saleAttrValueList);
    }

    @Override
    public void cancelSale(Long skuId) {
        //1、改数据库 sku_info 这个skuId的is_sale； 1上架  0下架
        skuInfoMapper.updateIsSale(skuId,1);
        //TODO 2、从es中删除这个商品
    }

    @Override
    public void onSale(Long skuId) {
        skuInfoMapper.updateIsSale(skuId,0);
        //TODO 2、给es中保存这个商品，商品就能被检索到了

    }

    //查询sku详情
    @Override
    public SkuDetailTo getSkuDetail(Long skuId) {
        SkuDetailTo skuDetailTo = new SkuDetailTo();
        //0 根据skuId查询到三级分类id
        SkuInfo skuInfo = skuInfoMapper.selectById(skuId);
        Long category3Id = skuInfo.getCategory3Id();

        // 2. sku基本信息
        skuDetailTo.setSkuInfo(skuInfo);

        //包含以下内容：1.sku所属分类完整信息
          CategoryViewTo categoryViewTo=baseCategory3Mapper.getCategoryView(category3Id);
          skuDetailTo.setCategoryView(categoryViewTo);


        // 3.sku图片信息
        List<SkuImage> skuImages= skuImageService.getSkuImage(skuId);
        skuInfo.setSkuImageList(skuImages);

        //实时价格查询


        BigDecimal price = skuInfo.getPrice();
        skuDetailTo.setPrice(price);


        // 4.sku所属spu当时定义的所有的销售属性名和值组合【标识出当前sku到底是spu的那种组合，页面要高亮显示】
//        List<SpuSaleAttr> saleAttrAndValueBySpuIdList = spuSaleAttrService.getSaleAttrAndValueBySpuId(skuInfo.getSpuId());
//        skuDetailTo.setSpuSaleAttrList(saleAttrAndValueBySpuIdList);

        List<SpuSaleAttr> saleAttrAndValueBySpuIdList = spuSaleAttrService.getSaleAttrAndValueAndAllAndOrder(skuInfo.getSpuId(),skuId);
        skuDetailTo.setSpuSaleAttrList(saleAttrAndValueBySpuIdList);


        //sku所有的兄弟产品的销售属性名和值组合关系全部查出来，并封装成json★
        //{"118|119":"50","118|119":"50"}例子
        String valueSkuJsonTos= spuSaleAttrService.getAllBrotherJson(skuInfo.getSpuId());
        skuDetailTo.setValuesSkuJson(valueSkuJsonTos);




        //5. sku类似推荐········未做
        //6. sku详情介绍
        //7. sku规格参数
        //8. sku售后 评论··········未做


        return skuDetailTo;
    }

//    @Override//全查性能低 老师自定义
//    public BigDecimal getNowPrice(Long skuId) {
//
//        return price;
//    }
}





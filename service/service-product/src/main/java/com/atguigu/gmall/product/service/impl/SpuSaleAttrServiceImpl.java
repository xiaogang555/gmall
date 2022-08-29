package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.common.util.Jsons;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.model.product.SpuSaleAttrValue;
import com.atguigu.gmall.model.to.ValueSkuJsonTo;
import com.atguigu.gmall.product.service.SpuSaleAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.atguigu.gmall.product.service.SpuSaleAttrService;
import com.atguigu.gmall.product.mapper.SpuSaleAttrMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
* @author 孙永刚
* @description 针对表【spu_sale_attr(spu销售属性)】的数据库操作Service实现
* @createDate 2022-08-24 16:43:06
*/
@Service
public class SpuSaleAttrServiceImpl extends ServiceImpl<SpuSaleAttrMapper, SpuSaleAttr>
    implements SpuSaleAttrService{

    @Resource
    SpuSaleAttrService spuSaleAttrService;

    @Autowired
    SpuSaleAttrValueService spuSaleAttrValueService;
    @Resource
    SpuSaleAttrMapper spuSaleAttrMapper;

    @Override
    public List<SpuSaleAttr> getSaleAttrAndValueBySpuId(Long spuId) {

        LambdaQueryWrapper<SpuSaleAttr> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<SpuSaleAttr> wrapper = queryWrapper.eq(SpuSaleAttr::getSpuId, spuId).orderByAsc(SpuSaleAttr::getSpuId);

        List<SpuSaleAttr> list = spuSaleAttrService.list(wrapper);

        for (SpuSaleAttr saleAttr : list) {
            Long id = saleAttr.getBaseSaleAttrId();

            LambdaQueryWrapper<SpuSaleAttrValue> queryWrapper1 = new LambdaQueryWrapper<>();

            LambdaQueryWrapper<SpuSaleAttrValue> wrapper1 = queryWrapper1.eq(SpuSaleAttrValue::getBaseSaleAttrId, id)
                    .eq(SpuSaleAttrValue::getSpuId, spuId).orderByAsc(SpuSaleAttrValue::getBaseSaleAttrId);



            List<SpuSaleAttrValue> list1 = spuSaleAttrValueService.list(wrapper1);

            saleAttr.setSpuSaleAttrValueList(list1);
        }
        return list;
    }

    @Override
    public List<SpuSaleAttr> getSaleAttrAndValueAndAllAndOrder(Long spuId, Long skuId) {

        return spuSaleAttrMapper.getSaleAttrAndValueAndAllAndOrder(spuId,skuId);
    }

    @Override
    public String getAllBrotherJson(Long spuId) {
       List<ValueSkuJsonTo> valueSkuJsonTos= spuSaleAttrMapper.getAllBrotherJson(spuId);
       //需要转为{"50":"118|119"}例子(反过来)

        HashMap<String, Long> map = new HashMap<>();
        for (ValueSkuJsonTo valueSkuJsonTo : valueSkuJsonTos) {
            String valueJson = valueSkuJsonTo.getValueJson();
            Long skuId = valueSkuJsonTo.getSkuId();
            map.put(valueJson,skuId);
        }
        String s = Jsons.toString(map);

        return  s;
    }


}





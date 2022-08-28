package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 孙永刚
* @description 针对表【spu_sale_attr(spu销售属性)】的数据库操作Service
* @createDate 2022-08-24 16:43:06
*/
public interface SpuSaleAttrService extends IService<SpuSaleAttr> {

    List<SpuSaleAttr> getSaleAttrAndValueBySpuId(Long spuId);

    List<SpuSaleAttr> getSaleAttrAndValueAndAllAndOrder(Long spuId, Long skuId);
}

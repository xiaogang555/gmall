package com.atguigu.gmall.model.to;

import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuDetailTo {

    private CategoryViewTo  categoryView;//sku所属分类完整信息

    private SkuInfo skuInfo;//sku基本信息

    private BigDecimal price;

    private List<SpuSaleAttr> spuSaleAttrList;//spu所有销售属性


    private   String valuesSkuJson;//
}

package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.SkuImage;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.atguigu.gmall.product.service.SkuImageService;
import com.atguigu.gmall.product.mapper.SkuImageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 孙永刚
* @description 针对表【sku_image(库存单元图片表)】的数据库操作Service实现
* @createDate 2022-08-24 16:43:06
*/
@Service
public class SkuImageServiceImpl extends ServiceImpl<SkuImageMapper, SkuImage>
    implements SkuImageService{
    @Resource
    SkuImageMapper skuImageMapper;

    @Override
    public List<SkuImage> getSkuImage(Long skuId) {
        LambdaQueryWrapper<SkuImage> queryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<SkuImage> wrapper = queryWrapper.eq(SkuImage::getSkuId, skuId);
        List<SkuImage> skuImages = skuImageMapper.selectList(wrapper);

        return skuImages;
    }
}





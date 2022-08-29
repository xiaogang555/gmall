package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.model.to.CategoryViewTo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.atguigu.gmall.product.service.BaseCategory3Service;
import com.atguigu.gmall.product.mapper.BaseCategory3Mapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 孙永刚
* @description 针对表【base_category3(三级分类表)】的数据库操作Service实现
* @createDate 2022-08-23 10:21:59
*/
@Service
public class BaseCategory3ServiceImpl extends ServiceImpl<BaseCategory3Mapper, BaseCategory3>
    implements BaseCategory3Service{

    @Resource
    BaseCategory3Mapper baseCategory3Mapper;
    @Override
    public List<BaseCategory3> getCategory1Child(Long c2Id) {
        LambdaQueryWrapper<BaseCategory3> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BaseCategory3::getCategory2Id,c2Id);
        return baseCategory3Mapper.selectList(queryWrapper);
    }

    @Override
    public CategoryViewTo getCategoryView(Long category3Id) {
        CategoryViewTo categoryViewTo=baseCategory3Mapper.getCategoryView(category3Id);
        return categoryViewTo;
    }
}





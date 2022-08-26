package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.to.CategoryTreeto;
import com.atguigu.gmall.product.mapper.BaseCategory2Mapper;
import com.atguigu.gmall.product.service.BaseCategory2Service;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class BaseCategory2ServiceImpl extends ServiceImpl<BaseCategory2Mapper, BaseCategory2> implements BaseCategory2Service {
    @Resource
    BaseCategory2Mapper baseCategory2Mapper;
    @Override
    public List<BaseCategory2> getCategory1Child(Long c1Id) {
        LambdaQueryWrapper<BaseCategory2> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BaseCategory2::getCategory1Id,c1Id);

        return baseCategory2Mapper.selectList(queryWrapper);
    }

    @Override
    public List<CategoryTreeto> getAllCategoryWithTree() {



        return baseCategory2Mapper.getAllCategoryWithTree();
    }
}

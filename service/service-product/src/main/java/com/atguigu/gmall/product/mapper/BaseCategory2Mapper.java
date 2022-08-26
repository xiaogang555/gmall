package com.atguigu.gmall.product.mapper;

import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.to.CategoryTreeto;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface BaseCategory2Mapper extends BaseMapper<BaseCategory2> {
    /**
     * date 2022/8/27 1:27
     * @message 查询所有分类以及子分类
     * @author 孙永刚
     */
    List<CategoryTreeto> getAllCategoryWithTree();
}

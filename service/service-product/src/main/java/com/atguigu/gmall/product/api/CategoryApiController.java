package com.atguigu.gmall.product.api;


import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.CategoryTreeto;
import com.atguigu.gmall.product.service.BaseCategory2Service;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inner/rpc/product")//命名规范
public class CategoryApiController {
    /**
     * date 2022/8/27 1:12
     * @message 查询所有分类并封装成树形菜单结构
     * @author 孙永刚
    */
    @Autowired
    BaseCategory2Service baseCategory2Service;


    @ApiOperation(value = "三级分类封装")
    @RequestMapping(value = "/category/tree",method = RequestMethod.GET)
    public Result getAllCategoryWithTree(){

        List<CategoryTreeto> categoryTreetos =baseCategory2Service.getAllCategoryWithTree();

    return Result.ok(categoryTreetos);
    }
}

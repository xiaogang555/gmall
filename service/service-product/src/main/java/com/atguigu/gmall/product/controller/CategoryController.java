package com.atguigu.gmall.product.controller;


import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseCategory1;
import com.atguigu.gmall.model.product.BaseCategory2;
import com.atguigu.gmall.model.product.BaseCategory3;
import com.atguigu.gmall.product.service.BaseCategory1Service;
import com.atguigu.gmall.product.service.BaseCategory2Service;
import com.atguigu.gmall.product.service.BaseCategory3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@CrossOrigin
@RestController
@RequestMapping("/admin/product")
public class CategoryController {
    @Autowired
    BaseCategory1Service baseCategory1Service;
    @Autowired
    BaseCategory2Service baseCategory2Service;
    @Autowired
    BaseCategory3Service baseCategory3Service;
    //查询一级分类
    @RequestMapping(value = "/getCategory1",method = RequestMethod.GET)
    public Result getCategory1(){
        List<BaseCategory1> list1 = baseCategory1Service.list();
        return Result.ok(list1);
    }

    //查询二级分类 /admin/product/getCategory2/3
    @RequestMapping(value = "/getCategory2/{c1Id}",method =RequestMethod.GET)
    public Result getCategory2(@PathVariable Long c1Id){
        List<BaseCategory2> list2= baseCategory2Service.getCategory1Child(c1Id);

    return Result.ok(list2);
    }

    @RequestMapping(value = "/getCategory3/{c2Id}",method =RequestMethod.GET)
    public Result getCategory3(@PathVariable Long c2Id){
        List<BaseCategory3> list3= baseCategory3Service.getCategory1Child(c2Id);

        return Result.ok(list3);
    }
}

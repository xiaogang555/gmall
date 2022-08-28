package com.atguigu.gmall.web.feign;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.CategoryTreeto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/inner/rpc/product")
@FeignClient("service-product")
public interface CategoryFeignClient {

    //以下方法可以改写为
//    @GetMapping("/api/inner/rpc/product/category/tree")
//    Result<List<CategoryTreeto>> getCategoryTree();
    @GetMapping("/category/tree")
    Result<List<CategoryTreeto>> getAllCategoryWithTree(); //与apicontroller保持一致



}

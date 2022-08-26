package com.atguigu.gmall.product.controller;


import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin/product")
@RestController
public class BaseTrademarkController {///baseTrademark/{page}/{limit}
    @Autowired
    BaseTrademarkService baseTrademarkService;

    //分页查询完以后数据没问题   但是分页插件有问题  去官网看下怎么配置
    @RequestMapping(value = "/baseTrademark/{pagenumber}/{pagesize}",method = RequestMethod.GET)
    public Result getBaseTrademark(@PathVariable Long pagenumber,
                                   @PathVariable Long pagesize){

        IPage<BaseTrademark> page=new Page<>(pagenumber,pagesize);

        IPage<BaseTrademark> pageResult = baseTrademarkService.page(page);

        return Result.ok(pageResult);
    }

    //修改  先查后改
    @RequestMapping(value = "/baseTrademark/get/{id}",method =RequestMethod.GET)
    public Result getBaseTrademark(@PathVariable Long id){
        BaseTrademark  trademarkInfo  = baseTrademarkService.getById(id);
        return Result.ok(trademarkInfo);
    }
    
    @RequestMapping(value = "/baseTrademark/update",method =RequestMethod.PUT)///baseTrademark/update
    public Result updateBaseTrademark(@RequestBody BaseTrademark baseTrademark){

        baseTrademarkService.updateById(baseTrademark);

    return Result.ok();
    }

    @RequestMapping(value = "/baseTrademark/save", method = RequestMethod.POST)///baseTrademark/update
    public Result saveBaseTrademark(@RequestBody BaseTrademark baseTrademark) {

        baseTrademarkService.save(baseTrademark);

        return Result.ok();
    }
    @RequestMapping(value = "/baseTrademark/remove/{id}", method = RequestMethod.DELETE)///baseTrademark/update
    public Result saveBaseTrademark(@PathVariable Long id) {

        baseTrademarkService.removeById(id);

        return Result.ok();
    }
}

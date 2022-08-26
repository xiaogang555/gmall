package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.service.BaseAttrValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/product")
@RestController
//查询平台属性
public class BaseAttrController {
    @Autowired
    BaseAttrInfoService baseAttrInfoService;

    @Autowired
    BaseAttrValueService baseAttrValueService;
    @RequestMapping(value = "/attrInfoList/{category1Id}/{category2Id}/{category3Id}",method = RequestMethod.GET)//attrInfoList/2/0/0
    public Result getAttrInfoList(@PathVariable Long category1Id,
                                  @PathVariable Long category2Id,
                                  @PathVariable Long category3Id){
        List<BaseAttrInfo>  infos= baseAttrInfoService.getattrInfoAndValueByCategoryId(category1Id,category2Id,category3Id);
    return Result.ok(infos);
    }


    //保存哦平台属性
    @RequestMapping(value = "/saveAttrInfo",method =RequestMethod.POST)
    public Result saveAttrInfo( @RequestBody BaseAttrInfo baseAttrInfo){
         baseAttrInfoService.saveAttrInfo(baseAttrInfo);

        return Result.ok();
    }
    //修改平台属性先查询   然后新增和修改方法二合一  通过判断带来的id来确定是新增还是修改
    @RequestMapping(value = "/getAttrValueList/{attrId}",method =RequestMethod.GET)//getAttrValueList/{attrId}
    public Result getAttrValueList(@PathVariable Long attrId){
       List<BaseAttrValue> values= baseAttrValueService.getAttrValueList(attrId);
    return Result.ok(values);
    }
}

package com.atguigu.gmall.product.service;


import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 孙永刚
* @description 针对表【base_attr_info(属性表)】的数据库操作Service
* @createDate 2022-08-23 20:50:29
*/
public interface BaseAttrInfoService extends IService<BaseAttrInfo> {

//@Param("houseId") Long houseId  实例
    List<BaseAttrInfo> getattrInfoAndValueByCategoryId(Long category1Id, Long category2Id, Long category3Id);

    void saveAttrInfo(BaseAttrInfo baseAttrInfo);
}

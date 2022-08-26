package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.model.base.BaseEntity;
import com.atguigu.gmall.model.product.BaseAttrInfo;
import com.atguigu.gmall.model.product.BaseAttrValue;
import com.atguigu.gmall.product.mapper.BaseAttrValueMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
* @author 孙永刚
* @description 针对表【base_attr_info(属性表)】的数据库操作Service实现
* @createDate 2022-08-23 20:50:29
*/
@Service
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo>
    implements BaseAttrInfoService{
    @Resource
    BaseAttrInfoMapper baseAttrInfoMapper;

    @Resource
    BaseAttrValueMapper baseAttrValueMapper;


    @Override
    public List<BaseAttrInfo> getattrInfoAndValueByCategoryId( Long category1Id, Long category2Id, Long category3Id) {
        List<BaseAttrInfo>  infos=baseAttrInfoMapper.getattrInfoAndValueByCategoryId(category1Id,category2Id,category3Id);
        return infos;
    }

    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        if (baseAttrInfo.getId()==null){

            //保存属性名
            baseAttrInfoMapper.insert(baseAttrInfo);
            Long id = baseAttrInfo.getId();
            //保存属性值
            List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
            for (BaseAttrValue baseAttrValue : attrValueList) {
                //回填属性名记录的自增id
                baseAttrValue.setAttrId(id);

                baseAttrValueMapper.insert(baseAttrValue);
            }
        }else {



            //修改 属性名
            baseAttrInfoMapper.updateById(baseAttrInfo);


            //修改属性值  1.全删然后新增  存在引用失效（有个商品正好引用了这个属性值） 行不通
            //dotrue:
            List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();


            ArrayList<Long> list = new ArrayList<>();

            for (BaseAttrValue value : attrValueList) {
                Long id = value.getId();
                if (id!=null){
                    list.add(id);
                }

            }//收集前端传来的id
            if (list.size()>0){//减
                LambdaQueryWrapper<BaseAttrValue> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(BaseAttrValue::getAttrId, baseAttrInfo.getId());
                queryWrapper.notIn(BaseEntity::getId,list);

                baseAttrValueMapper.delete(queryWrapper);
            }

            for (BaseAttrValue baseAttrValue : attrValueList) {
                if (baseAttrValue.getId() !=null){//改

                    baseAttrValueMapper.updateById(baseAttrValue);
                }else if(baseAttrValue.getId() ==null){//增
                    baseAttrValue.setAttrId(baseAttrInfo.getId());
                    baseAttrValueMapper.insert(baseAttrValue);
                }
            }
        }

    }
}





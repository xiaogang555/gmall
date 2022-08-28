package com.atguigu.gmall.web.controlller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.to.CategoryTreeto;
import com.atguigu.gmall.web.feign.CategoryFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    CategoryFeignClient categoryFeignClient;

    @GetMapping({"/","/index"})
    public String indexPage(Model model){



        //远程调用
        Result<List<CategoryTreeto>> allCategoryWithTree = categoryFeignClient.getAllCategoryWithTree();
        //查出所有菜单
        if (allCategoryWithTree.isOk()){
            //远程成功
            List<CategoryTreeto> data = allCategoryWithTree.getData();
            model.addAttribute("list",data);
        }

        //  classpath:/templates/index/index.html
        return "index/index";//页面的逻辑视图名
    }
}

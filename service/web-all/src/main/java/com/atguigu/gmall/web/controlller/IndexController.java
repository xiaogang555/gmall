package com.atguigu.gmall.web.controlller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/","/index"})
    public String indexPage(Model model){

        //查出所有菜单


        model.addAttribute("list",null);
        //  classpath:/templates/index/index.html
        return "index/index";//页面的逻辑视图名
    }
}

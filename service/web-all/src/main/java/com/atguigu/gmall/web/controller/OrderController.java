package com.atguigu.gmall.web.controller;

import org.checkerframework.checker.units.qual.mol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

//    @Autowired
//    OrderFeignClient orderFeignClient;
    /**
     * 跳到 订单确认页
     * @return
     */
    @GetMapping("/trade.html")
    public String tradePage(Model model){


        //远程调用会透传用户信息
//        Result<OrderConfirmDataVo> orderConfirmData = orderFeignClient.getOrderConfirmData();

//        if(orderConfirmData.isOk()){
//            OrderConfirmDataVo data = orderConfirmData.getData();
//            //imgUrl、skuName、orderPrice、skuNum
//            model.addAttribute("detailArrayList",data.getDetailArrayList());
//            model.addAttribute("totalNum",data.getTotalNum());
//            model.addAttribute("totalAmount",data.getTotalAmount());
//            //用户收货地址列表
//            model.addAttribute("userAddressList",data.getUserAddressList());
//            //追踪订单的“交易号”
//            model.addAttribute("tradeNo",data.getTradeNo());
//        }

        return "order/trade";
    }
}

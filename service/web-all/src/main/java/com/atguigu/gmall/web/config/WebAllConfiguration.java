//package com.atguigu.gmall.web.config;
//
//
//import com.atguigu.gmall.common.constant.SysRedisConst;
//import com.atguigu.gmall.web.controller.CartController;
//import feign.RequestInterceptor;
//import feign.RequestTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.Servlet;
//import javax.servlet.http.HttpServletRequest;
//
//@Configuration
//public class WebAllConfiguration  {
//
//    @Bean
//    public  RequestInterceptor userHeadInterceptor(){
//        return (template) -> {
//            //修改请求模板
//            //随时调用，获取当前请求
//            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            HttpServletRequest request = attributes.getRequest();
//            String userId = request.getHeader(SysRedisConst.USERID_HEADER);
//
//
//
//            //用户id头添加到feign的新请求中
//            template.header(SysRedisConst.USERID_HEADER,userId);
//
//        };
//    }
//}

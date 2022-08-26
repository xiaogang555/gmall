package com.atguigu.gmall.product.controller;


import com.atguigu.gmall.common.result.Result;

import com.atguigu.gmall.product.service.FileUploadService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/product")

public class FileUploadController  {///admin/product/fileUpload

    @Autowired
    FileUploadService fileUploadService;

    @ApiOperation(value = "文件上传")
    @RequestMapping(value = "/fileUpload",method = RequestMethod.POST)
    public Result fileUpload   (@RequestPart MultipartFile file) throws Exception {
        //收到前端的文件流，上传给minio  并返回这个文件在minio中的存储地址
        //以后用这个地址访问   数据库保存这个地址

        String url=fileUploadService.fileUpload(file);
    return Result.ok(url);
    }
}

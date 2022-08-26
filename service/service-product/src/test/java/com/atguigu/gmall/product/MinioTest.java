package com.atguigu.gmall.product;


import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.MinioException;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;


//@SpringBootTest//可以测试springboot所有组件功能
public class MinioTest {


    @Test
    //minio是一个时间敏感的中间件 需要去把docker中的主机时间设置对
    public void testFileUpload() throws Exception{
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient = new MinioClient("http://192.168.200.100:9000", "admin", "admin123456");

            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists("shangpinhui");
            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                // 创建一个名为asiatrip的存储桶，用于存储照片的zip文件。
                minioClient.makeBucket("newCreat");
            }

            // 使用putObject上传一个文件到存储桶中。
            //文件参数
            FileInputStream inputStream = new FileInputStream("C:\\Users\\孙永刚\\Desktop\\JAVA相关\\尚品汇\\资料\\03 商品图片\\品牌\\xiaomi.png");

            PutObjectOptions options = new PutObjectOptions(inputStream.available(), -1L);
            options.setContentType("image/png");

            minioClient.putObject("shangpinhui","xiaomi.png",inputStream,options);
            System.out.println("上传成功");
        } catch(MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }
}

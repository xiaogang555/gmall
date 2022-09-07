package com.atguigu.gmall.product;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import io.minio.errors.MinioException;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;

public class miniotest {



    @Test
    public void uploadFile() throws Exception {
        try {
            //Minio是一个时间敏感的中间件，
            //The difference between the request time and the server's time is too large

            // 1、使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            MinioClient minioClient =
                    new MinioClient("http://192.168.200.100:9000",
                            "admin",
                            "admin123456");

            //2、检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists("shangpinhui");

            if(isExist) {
                System.out.println("Bucket already exists.");
            } else {
                //3、如果桶不存在需要先创建一个桶
                minioClient.makeBucket("shangpinhui");
            }

            //4、使用putObject上传一个文件到存储桶中。
            /**
             * String bucketName, 桶名
             * String objectName, 对象名，也就是文件名
             * InputStream stream, 文件流  D:\0310\尚品汇\资料\03 商品图片\品牌\pingguo.png
             * PutObjectOptions options, 上传的参数设置
             */
            //文件流
            FileInputStream inputStream = new FileInputStream("C:\\Users\\孙永刚\\Desktop\\JAVA相关\\图片\\y.jpg");
            //文件上传参数：long objectSize, long partSize
            PutObjectOptions options = new PutObjectOptions(inputStream.available(), -1L);
            options.setContentType("image/png");
            //告诉Minio上传的这个文件的内容类型
            minioClient.putObject("shangpinhui","pingguo.png",
                    inputStream,
                    options
            );
            System.out.println("上传成功");
        } catch(MinioException e) {
            System.err.println("发生错误: " + e);
        }
    }


}

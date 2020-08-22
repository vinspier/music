package com.vinspier.upload;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
* @description: 上传服务
* @author: vinspeir
* @date:2020/8/16 16:24
*/

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.vinspier.upload.mapper")
public class UploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class,args);
    }

}

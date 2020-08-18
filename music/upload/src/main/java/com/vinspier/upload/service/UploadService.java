package com.vinspier.upload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {

    /**
     * 上传文件到本地服务器
     * */
    String upload(MultipartFile file);

    /**
     * fastDFS客户端上传到fastFDS服务器
     * 返回全路径
     * */
    String uploadImage(MultipartFile file);

    /**
     * 上传音频文件
     * 返回全路径
     * */
    String uploadAudio(MultipartFile file);

    /**
     * fastDFS客户端上传到fastFDS服务器
     * 返回全路径 生成缩略图
     * */
    String uploadFastThumb(MultipartFile file) throws IOException;

    /**
     * 下载文件
     * */
    byte[] download(String group,String path);

}

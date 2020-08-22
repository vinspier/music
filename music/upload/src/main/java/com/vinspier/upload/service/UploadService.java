package com.vinspier.upload.service;

import com.vinspier.upload.model.domain.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
    UploadFile uploadImage(MultipartFile file);

    /**
     * 上传音频文件
     * 返回全路径
     * */
    UploadFile uploadAudio(MultipartFile file);

    /**
     * fastDFS客户端上传到fastFDS服务器
     * 返回全路径 生成缩略图
     * */
    String uploadFastThumb(MultipartFile file) throws IOException;

}

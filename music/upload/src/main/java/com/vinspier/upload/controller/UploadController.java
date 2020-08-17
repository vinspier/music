package com.vinspier.upload.controller;


import com.alibaba.fastjson.JSONObject;
import com.vinspier.upload.model.BaseResult;
import com.vinspier.upload.service.UploadService;
import com.vinspier.upload.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传类
 * 图片上传是文件的传输，如果也经过Zuul网关的代理，
 * 文件就会经过多次网路传输，造成不必要的网络负担。
 * 在高并发时，可能导致网络阻塞，Zuul网关不可用。
 * 这样我们的整个系统就瘫痪了。
 *
 * zuul.ignored-patterns: /upload/**
 * zuul.ignored-services: upload-servie
 * */
@Controller
@RequestMapping(value = "upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 图片上传
     * @param file
     * @return
     */
    @PostMapping("image")
    @ResponseBody
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String url = this.uploadService.uploadImage(file);
        return JSONObject.toJSONString(ResultGenerator.genSuccess(url));
    }

    /**
     * 音频上传
     * 单文件
     * @param file
     * @return
     */
    @PostMapping("audio")
    @ResponseBody
    public String  uploadAudio(@RequestParam("file") MultipartFile file) throws IOException {
        String url = this.uploadService.uploadAudio(file);
        return JSONObject.toJSONString(ResultGenerator.genSuccess(url));
    }

}

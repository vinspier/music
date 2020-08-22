package com.vinspier.upload.controller;


import com.vinspier.upload.model.base.BaseResult;
import com.vinspier.upload.model.vo.UploadFileVo;
import com.vinspier.upload.service.UploadService;
import com.vinspier.upload.util.FileUtil;
import com.vinspier.upload.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

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
@Api(value = "/UploadController", tags = "文件上传服务 - vinspier")
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
    @ApiOperation(value = "图片上传")
    public BaseResult<UploadFileVo> uploadImage(@RequestParam("file") MultipartFile file){
        UploadFileVo uploadFileVo = new UploadFileVo();
        BeanUtils.copyProperties(uploadService.uploadImage(file),uploadFileVo);
        return ResultGenerator.genSuccess(uploadFileVo);
    }

    /**
     * 音频上传
     * 单文件
     * @param file
     * @return
     */
    @PostMapping("audio")
    @ResponseBody
    @ApiOperation(value = "音频上传")
    public BaseResult<UploadFileVo> uploadAudio(@RequestParam("file") MultipartFile file){
        UploadFileVo uploadFileVo = new UploadFileVo();
        BeanUtils.copyProperties(uploadService.uploadAudio(file),uploadFileVo);
        return ResultGenerator.genSuccess(uploadFileVo);
    }

}

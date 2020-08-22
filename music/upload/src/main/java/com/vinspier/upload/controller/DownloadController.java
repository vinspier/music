package com.vinspier.upload.controller;

import com.vinspier.upload.model.base.BaseResult;
import com.vinspier.upload.model.vo.UploadFileVo;
import com.vinspier.upload.service.DownloadService;
import com.vinspier.upload.util.FileUtil;
import com.vinspier.upload.util.ResultGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 大鱼网络科技有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description: 文件下载服务 - vinspier <br>
 * @Project: <br>
 * @CreateDate: Created in 2020/8/22 22:24 <br>
 * @Author: <a href="fanxb@haibaobaoxian.cn">fxb</a>
 */
@Controller
@RequestMapping(value = "download")
@Api(value = "/DownloadController", tags = "文件下载服务 - vinspier")
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    /**
     * 下载文件
     * */
    @GetMapping("/{fileId}")
    @ResponseBody
    @ApiOperation(value = "文件ID下载")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "fileId",value = "文件ID",required = true)
    )
    public void download(@PathVariable String fileId, HttpServletResponse response){
        downloadService.download(fileId,response);
    }

    /**
     * 下载文件
     * */
    @GetMapping("/{group}/{path}")
    @ResponseBody
    @ApiOperation(value = "文件下载 组名 + 路径")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "group", value = "文件组名", required = true),
            @ApiImplicitParam(name = "path", value = "文件相对路径", required = true)
    })
    public void download(@PathVariable String group, @PathVariable String path, HttpServletResponse response){
        byte[] fileByte = downloadService.download(group,path);
        FileUtil.downloadFileByEncode_gb2312(response,fileByte,"test-fileName" + UUID.randomUUID().toString());
    }

    /**
     * @param: fileId
     * @Return: com.vinspier.upload.model.base.BaseResult<com.vinspier.upload.model.vo.UploadFileVo> <br>
     * @Decription: 通过fileId获取基础信息 <br>
     * @CreateDate: Created in 2020/8/22 22:38 <br>
     * @Author: <a href="fanxb@haibaobaoxian.cn">fxb</a>
     * @Modify:
     */
    @GetMapping("/getInfoByFileId/{fileId}")
    @ResponseBody
    @ApiOperation(value = "通过fileId获取基础信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileId", value = "文件fileId", required = true)
    })
    public BaseResult<UploadFileVo> getInfoByFileId(@PathVariable String fileId){
        return ResultGenerator.genSuccess(downloadService.getInfoByFileId(fileId));
    }


}

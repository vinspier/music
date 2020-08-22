package com.vinspier.upload.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.vinspier.upload.enums.ResultCode;
import com.vinspier.upload.exception.CustomizeException;
import com.vinspier.upload.model.domain.UploadFile;
import com.vinspier.upload.model.vo.UploadFileVo;
import com.vinspier.upload.service.DownloadService;
import com.vinspier.upload.service.IUploadFileService;
import com.vinspier.upload.util.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * 大鱼网络科技有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description: <br>
 * @Project: <br>
 * @CreateDate: Created in 2020/8/22 22:29 <br>
 * @Author: <a href="fanxb@haibaobaoxian.cn">fxb</a>
 */
@Service
public class DownloadServiceImpl implements DownloadService {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private IUploadFileService uploadFileService;

    @Override
    public byte[] download(String fileId, HttpServletResponse response) {
        QueryWrapper<UploadFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_id",fileId);
        // 数据库 读取信息
        UploadFile uploadFile = uploadFileService.getOne(queryWrapper);
        if (uploadFile == null){
            throw new CustomizeException(ResultCode.FILE_NOT_EXISTED);
        }
        // FastFDS 下载源文件
        byte[] fileByte = this.storageClient.downloadFile(uploadFile.getGroupName(),uploadFile.getServerPath(),(InputStream is) -> is.readAllBytes());
        //返回 文件流 至 客户端
        FileUtil.downloadFileByEncode_gb2312(response,fileByte,uploadFile.getOriginalFileName());
        return fileByte;
    }

    @Override
    public byte[] download(String group, String path) {
        byte[] fileByte = this.storageClient.downloadFile(group,path,(InputStream is) -> is.readAllBytes());
        return fileByte;
    }

    @Override
    public UploadFileVo getInfoByFileId(String fileId) {
        QueryWrapper<UploadFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("file_id",fileId);
        // 数据库 读取信息
        UploadFile uploadFile = uploadFileService.getOne(queryWrapper);
        if (uploadFile == null){
            return null;
        }
        UploadFileVo uploadFileVo = new UploadFileVo();
        BeanUtils.copyProperties(uploadFile,uploadFileVo);
        return uploadFileVo;
    }
}

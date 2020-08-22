package com.vinspier.upload.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tobato.fastdfs.domain.StorePath;
import com.vinspier.upload.config.UploadProperties;
import com.vinspier.upload.mapper.UploadFileMapper;
import com.vinspier.upload.model.domain.UploadFile;
import com.vinspier.upload.service.IUploadFileService;
import com.vinspier.upload.util.FileGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * vinspier 版权所有 © Copyright 2018<br>
 *
 * @Description: 文件信息表 服务实现类<br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020-08-22 <br>
 * @Author: vinspier
 */
@Service
public class UploadFileServiceImpl extends ServiceImpl<UploadFileMapper, UploadFile> implements IUploadFileService {

    @Autowired
    private UploadProperties uploadProperties;

    @Override
    public UploadFile saveUpload(MultipartFile file, StorePath storePath,String ext) {
        // 生成系统文件模型
        UploadFile uploadFile = FileGenerator.genFormMultipartFile(file,storePath.getGroup(),storePath.getPath(),uploadProperties.getServerPath() + storePath.getFullPath(),ext);
        super.save(uploadFile);
        return uploadFile;
    }
}

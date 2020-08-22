package com.vinspier.upload.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.tobato.fastdfs.domain.StorePath;
import com.vinspier.upload.model.domain.UploadFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * vinspier 版权所有 © Copyright 2018<br>
 *
 * @Description: 文件信息表 服务类<br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020-08-22 <br>
 * @Author: vinspier
 */
public interface IUploadFileService extends IService<UploadFile> {

    /**
     * @param: file
     * @Return: com.vinspier.upload.model.UploadFile <br>
     * @Decription: 存储上传的文件 <br>
     * @CreateDate: Created in 2020/8/22 20:19 <br>
     * @Author: <a href="fanxb@haibaobaoxian.cn">fxb</a>
     * @Modify:
     */
    UploadFile saveUpload(MultipartFile file, StorePath storePath,String ext);

}

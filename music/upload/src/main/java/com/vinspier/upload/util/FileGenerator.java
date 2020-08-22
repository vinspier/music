package com.vinspier.upload.util;

import com.vinspier.upload.enums.DataState;
import com.vinspier.upload.model.domain.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 大鱼网络科技有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description: 文件基础类信息生成 <br>
 * @Project: <br>
 * @CreateDate: Created in 2020/8/22 20:03 <br>
 * @Author: <a href="fanxb@haibaobaoxian.cn">fxb</a>
 */
public class FileGenerator {

    private FileGenerator() {

    }

    public static UploadFile genEmpty(){
        return new UploadFile();
    }

    public static UploadFile genFormMultipartFile(MultipartFile file){
        return genFormMultipartFile(file,null,null,null,null);
    }

    public static UploadFile genFormMultipartFile(MultipartFile file,String groupName,String serverPath,String absolutePath,String ext){
        UploadFile uploadFile = new UploadFile();
        uploadFile.setFileName(file.getName());
        uploadFile.setOriginalFileName(file.getOriginalFilename());
        uploadFile.setContentType(file.getContentType());
        uploadFile.setSize(file.getSize());
        uploadFile.setGroupName(groupName);
        uploadFile.setServerPath(serverPath);
        uploadFile.setAbsolutePath(absolutePath);
        uploadFile.setStatus(DataState.NORMAL.getState());
        uploadFile.setCreateTime(new Date());
        uploadFile.setUpdateTime(new Date());
        uploadFile.setFileId(UuidUtil.genUuid());
        uploadFile.setExtension(ext);
        return uploadFile;
    }

}

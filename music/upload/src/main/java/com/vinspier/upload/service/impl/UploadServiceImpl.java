package com.vinspier.upload.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.vinspier.upload.config.UploadProperties;
import com.vinspier.upload.enums.ContentType;
import com.vinspier.upload.enums.ResultCode;
import com.vinspier.upload.exception.CustomizeException;
import com.vinspier.upload.model.domain.UploadFile;
import com.vinspier.upload.service.IUploadFileService;
import com.vinspier.upload.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class UploadServiceImpl implements UploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    private static final List<String> IMAGINE_TYPES = Arrays.asList("image/jpeg", "image/gif","image/png","image/jpg");
    private static final List<String> AUDIO_TYPES = Arrays.asList("audio/mp3","audio/wav");

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private UploadProperties uploadProperties;

    @Autowired
    private IUploadFileService uploadFileService;


    @Override
    @Transactional
    public String upload(MultipartFile file){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        validateFile(file, ContentType.IMAGE.getContentType(),IMAGINE_TYPES);
        try {
            File uploadFile = new File(uploadProperties.getLocalPath() + dateFormat.format(new Date()) + "\\" + file.getOriginalFilename());
            if (!uploadFile.getParentFile().exists()){
                uploadFile.getParentFile().mkdirs();
            }
            // 保存到服务器
            // ToDo 保存到文件服务器
            file.transferTo(uploadFile);
            // 生成url地址，返回
            return uploadProperties.getLocalPath() + "\\" + file.getOriginalFilename();
        } catch (IOException e) {
            LOGGER.info("服务器内部错误：{}", file.getOriginalFilename());
            throw new CustomizeException(ResultCode.FILE_WRITE_ERROR);
        }
    }

    @Override
    @Transactional
    public UploadFile uploadImage(MultipartFile file){
        validateFile(file,ContentType.IMAGE.getContentType(),IMAGINE_TYPES);
        try {
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            // 上传至 FastFDS 文件服务器
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);
            return uploadFileService.saveUpload(file,storePath,ext);
        } catch (IOException e) {
            LOGGER.info("文件上传至服务器失败：{}", file.getOriginalFilename());
            throw new CustomizeException(ResultCode.UPLOAD_FAILED);
        }
    }

    @Override
    @Transactional
    public UploadFile uploadAudio(MultipartFile file){
        validateFile(file,ContentType.AUDIO.getContentType(),AUDIO_TYPES);
        try {
            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);
            return uploadFileService.saveUpload(file,storePath,ext);
        } catch (IOException e) {
            LOGGER.info("文件上传至服务器失败：{}", file.getOriginalFilename());
            throw new CustomizeException(ResultCode.UPLOAD_FAILED);
        }
    }

    @Override
    @Transactional
    public String uploadFastThumb(MultipartFile file) throws IOException{
        validateFile(file,ContentType.IMAGE.getContentType(),IMAGINE_TYPES);
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), ext, null);
        return uploadProperties.getServerPath() + storePath.getFullPath();
    }

    /**
     * 验证文件格式
     * */
    private void validateFile(MultipartFile file,String validContentType,List<String> fileExtensions){
        String originalFilename = file.getOriginalFilename();
        // 校验文件的类型
        String contentType = file.getContentType();
        if (!contentType.startsWith(validContentType)){
            // 文件类型不合法，直接返回null
            LOGGER.info("文件类型不合法: current file content type is [{}] , should be [{}]", originalFilename,fileExtensions.toString());
            throw new CustomizeException(ResultCode.FILE_INVALID);
        }
        // 校验文件的内容
        // BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        if (file.getSize() <= 0){
            LOGGER.info("文件内容不合法：file content is not allowed empty");
            throw new CustomizeException(ResultCode.FILE_EMPTY);
        }
    }

}

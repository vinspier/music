package com.vinspier.upload.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.vinspier.upload.config.UploadProperties;
import com.vinspier.upload.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class UploadServiceImpl implements UploadService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadService.class);

    private static final String IMAGE_CONTENT_TYPE = "image/";
    private static final List<String> IMAGINE_TYPES = Arrays.asList("image/jpeg", "image/gif","image/png","image/jpg");
    private static final String AUDIO_CONTENT_TYPE = "audio/";
    private static final List<String> AUDIO_TYPES = Arrays.asList("audio/mp3","audio/wav");

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private UploadProperties uploadProperties;

    @Override
    public String upload(MultipartFile file) throws IOException{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String validateResult = validateFile(file,IMAGE_CONTENT_TYPE,IMAGINE_TYPES);
        if (StringUtils.hasText(validateResult)){
            return validateResult;
        }
        // ToDo 定义全局报错返回
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
            e.printStackTrace();
            return "文件写入磁盘错误";
        }
    }

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        String validateResult = validateFile(file,IMAGE_CONTENT_TYPE,IMAGINE_TYPES);
        if (StringUtils.hasText(validateResult)){
            return validateResult;
        }
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);
        // 生成url地址，返回
        return uploadProperties.getServerPath() + storePath.getFullPath();
    }

    @Override
    public String uploadAudio(MultipartFile file) throws IOException {
        String validateResult = validateFile(file,AUDIO_CONTENT_TYPE,AUDIO_TYPES);
        if (StringUtils.hasText(validateResult)){
            return validateResult;
        }

        return null;
    }

    @Override
    public String uploadFastThumb(MultipartFile file) throws IOException{
        String validateResult = validateFile(file,IMAGE_CONTENT_TYPE,IMAGINE_TYPES);
        if (StringUtils.hasText(validateResult)){
            return validateResult;
        }
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), ext, null);
        return uploadProperties.getServerPath() + storePath.getFullPath();
    }

    /**
     * 验证文件格式
     * */
    // ToDo 校验不合格 抛出全局错误
    private String validateFile(MultipartFile file,String validContentType,List<String> fileExtensions) throws IOException{
        String originalFilename = file.getOriginalFilename();
        // 校验文件的类型
        String contentType = file.getContentType();
        if (!contentType.startsWith(validContentType)){
            // 文件类型不合法，直接返回null
            LOGGER.info("文件类型不合法: current file content type is [{}] , should be [{}]", originalFilename,fileExtensions.toString());
            return "请上传合法格式文件";
        }
        // 校验文件的内容
        // BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
        if (file.getSize() <= 0){
            LOGGER.info("文件内容不合法：file content is not allowed empty");
            return "文件内容不合法：file content is not allowed empty";
        }
        return null;
    }
}

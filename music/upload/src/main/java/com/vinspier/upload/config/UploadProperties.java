package com.vinspier.upload.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
* @description: 上传参数配置类
* @author: vinspeir
* @date:2020/8/16 16:36
*/
@Component
@ConfigurationProperties(prefix = "upload")
public class UploadProperties {

    // 本地文件存储路径
    private String localPath;

    // 服务器文件访问路径
    private String serverPath;

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }
}

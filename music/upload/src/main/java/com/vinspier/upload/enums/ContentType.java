package com.vinspier.upload.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @description: 请求体content type 配置类
 * @author: vinspeir
 * @date:2020/8/17 23:15
 */
@Getter
@ToString
@AllArgsConstructor
public enum ContentType {

    IMAGE("image/"),
    AUDIO("audio/"),
    VIDEO("video/");

    private String contentType;

}

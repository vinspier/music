package com.vinspier.upload.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
* @description: 全局返回信息 配置类
* @author: vinspeir
* @date:2020/8/17 23:15
*/
@Getter
@ToString
@AllArgsConstructor
public enum  ResultCode {

    UPLOAD_SUCCESS(2000,"upload success"),
    FETCH_DATA_NONE(2004,"no data in target server"),
    PARAMETER_WRONG(4001,"invalid params "),
    FILE_INVALID(4010,"invalid file content type"),
    FILE_EMPTY(4011,"empty file is not allowed"),
    UPLOAD_FAILED(5000,"upload failed, server error"),
    ACCESS_TOKEN_NONE(5001,"missing necessary valid message access token"),
    FILE_WRITE_ERROR(5002,"file write into server error"),
    USER_NOT_EXIST(5003,"user does not existed");

    private Integer code;

    private String msg;

}

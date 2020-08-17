package com.vinspier.upload.handler;

import com.alibaba.fastjson.JSONObject;
import com.vinspier.upload.enums.ResultCode;
import com.vinspier.upload.exception.CustomizeException;
import com.vinspier.upload.util.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: CustomizeExceptionHandler
 * @Description: 自定义全局异常处理类
 * @Author:
 * @Date: 2020/3/19 11:29
 * @Version V1.0
 **/

@ControllerAdvice
public class CustomizeExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomizeExceptionHandler.class);


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String handleException(Exception e){
        logger.error(e.getMessage(),e);
        return JSONObject.toJSONString(ResultGenerator.genFail(ResultCode.UPLOAD_FAILED));
    }

    @ExceptionHandler(value = CustomizeException.class)
    @ResponseBody
    public String handleUserException(CustomizeException e){
        logger.error(e.getMessage(),e);
        return JSONObject.toJSONString(ResultGenerator.genFail(e.getResultCode()));
    }

}

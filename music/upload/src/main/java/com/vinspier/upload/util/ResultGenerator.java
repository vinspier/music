package com.vinspier.upload.util;

import com.vinspier.upload.enums.ResultCode;
import com.vinspier.upload.model.base.BaseResult;

/**
* @description: 生成模板返回数据模型
* @author: vinspeir
* @date:2020/8/16 17:09
*/
public class ResultGenerator {

    private ResultGenerator() {

    }

    public static BaseResult genResult(Integer code,String msg,boolean success){
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(code);
        baseResult.setMsg(msg);
        baseResult.setSuccess(success);
        return baseResult;
    }

    public static BaseResult genResult(ResultCode resultCode,boolean success,Object data){
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(resultCode.getCode());
        baseResult.setMsg(resultCode.getMsg());
        baseResult.setSuccess(success);
        baseResult.setData(data);
        return baseResult;
    }

    public static BaseResult genDefaultSuccess(){
        return genResult(200,"success",true);
    }

    public static BaseResult genDefaultFail(){
        return genResult(200,"fail",false);
    }

    public static BaseResult genSuccess(ResultCode resultCode){
        return genResult(resultCode.getCode(),resultCode.getMsg(),true);
    }

    public static BaseResult genFail(ResultCode resultCode){
        return genResult(resultCode.getCode(),resultCode.getMsg(),false);
    }

    public static BaseResult genSuccess(Object data){
        return genResult(ResultCode.UPLOAD_SUCCESS,true,data);
    }

}

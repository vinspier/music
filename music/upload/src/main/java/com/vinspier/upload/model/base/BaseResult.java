package com.vinspier.upload.model.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel("通用接口返回信息")
public class BaseResult<T> {

    // 信息
    @ApiModelProperty(notes = "信息" ,example = "success")
    private String msg;

    // 信息代码
    @ApiModelProperty(notes = "代码" ,example = "2000")
    private int code;

    // 返回数据
    @ApiModelProperty(notes = "返回数据" ,example = "[]")
    private T data;

    // 是否成功
    @ApiModelProperty(notes = "是否成功" ,example = "true")
    private boolean success;

    // 当前页码
    @ApiModelProperty(notes = "当前页码" ,example = "1")
    private Integer pageNo;

    // 每页大小
    @ApiModelProperty(notes = "每页大小" ,example = "10")
    private Integer pageSize;

    // 总条数
    @ApiModelProperty(notes = "总条数" ,example = "100")
    private Integer totalCount;

}

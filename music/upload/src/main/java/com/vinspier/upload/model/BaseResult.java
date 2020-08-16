package com.vinspier.upload.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BaseResult<T> {

    // 信息
    private String msg;

    // 信息代码
    private int code;

    // 返回数据
    private T data;

    // 是否成功
    private boolean success;

    // 当前页码
    private Integer pageNo;

    // 每页大小
    private Integer pageSize;

    // 总条数
    private Integer totalCount;

}

package com.vinspier.upload.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

/**
 * 大鱼网络科技有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description: 数据状态枚举 <br>
 * @Project: <br>
 * @CreateDate: Created in 2020/8/22 19:55 <br>
 * @Author: <a href="fanxb@haibaobaoxian.cn">fxb</a>
 */
@Getter
@ToString
@AllArgsConstructor
public enum DataState {
    NORMAL(0,"正常"),
    DELETED(1,"逻辑删除");

    // 状态
    private Integer state;

    // 信息
    private String msg;

}

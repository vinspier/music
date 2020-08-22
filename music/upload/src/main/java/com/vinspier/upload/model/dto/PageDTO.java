package com.vinspier.upload.model.dto;

/**
 * 大鱼网络科技有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description: <br>
 * @Project: <br>
 * @CreateDate: Created in 2020/8/22 22:19 <br>
 * @Author: <a href="fanxb@haibaobaoxian.cn">fxb</a>
 */
public class PageDTO<T> extends PageModel {

    private T field;

    public PageDTO() {
    }

    public PageDTO(T field) {
        this.field = field;
    }
}

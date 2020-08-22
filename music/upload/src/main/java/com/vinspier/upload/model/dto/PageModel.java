package com.vinspier.upload.model.dto;

import lombok.Data;

/**
 * 大鱼网络科技有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description: <br>
 * @Project: <br>
 * @CreateDate: Created in 2020/8/22 22:19 <br>
 * @Author: <a href="fanxb@haibaobaoxian.cn">fxb</a>
 */

@Data
public class PageModel {

    private Integer pageNum = 1;
    private Integer pageSize = 20;
    private String orderBy;
    private boolean rowBoundsWithCount = true;
    private boolean reasonable = true;

    public PageModel() {
    }

}

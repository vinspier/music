package com.vinspier.upload.service;

import com.vinspier.upload.model.vo.UploadFileVo;

import javax.servlet.http.HttpServletResponse;

/**
 * 大鱼网络科技有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description: <br>
 * @Project: <br>
 * @CreateDate: Created in 2020/8/22 22:29 <br>
 * @Author: <a href="fanxb@haibaobaoxian.cn">fxb</a>
 */
public interface DownloadService {

    /**
     * 通过文件ID下载
     * */
    byte[] download(String fileId, HttpServletResponse response);

    /**
     * 下载文件
     * */
    byte[] download(String group,String path);

    /**
     * 获取文件信息
     * */
    UploadFileVo getInfoByFileId(String fileId);

}

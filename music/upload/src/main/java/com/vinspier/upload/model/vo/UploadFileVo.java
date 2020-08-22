package com.vinspier.upload.model.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * vinspier 版权所有 © Copyright 2020<br>
 *
 * @Description: 文件信息表<br>
 * @Project: hades <br>
 * @CreateDate: Created in 2020-08-22 <br>
 * @Author: vinspier
 */
@Data
@ApiModel(value = "文件视图数据模型")
public class UploadFileVo implements Serializable {

    @JSONField(name = "file_id")
    @TableField(value = "file_id")
    @ApiModelProperty(notes = "文件ID")
    private String fileId;

    @JSONField(name = "content_type")
    @TableField(value = "content_type")
    @ApiModelProperty(notes = "内容类型")
    private String contentType;

    @JSONField(name = "file_name")
    @TableField(value = "file_name")
    @ApiModelProperty(notes = "文件名称")
    private String fileName;

    @JSONField(name = "original_file_name")
    @TableField(value = "original_file_name")
    @ApiModelProperty(notes = "原文件名")
    private String originalFileName;

    @JSONField(name = "group_name")
    @TableField(value = "group_name")
    @ApiModelProperty(notes = "分组名")
    private String groupName;

    @JSONField(name = "server_path")
    @TableField(value = "server_path")
    @ApiModelProperty(notes = "服务器相对路径")
    private String serverPath;

    @JSONField(name = "absolute_path")
    @TableField(value = "absolute_path")
    @ApiModelProperty(notes = "服务器全路径")
    private String absolutePath;

    @JSONField(name = "size")
    @TableField(value = "size")
    @ApiModelProperty(notes = "大小 byte")
    private Long size;

    @JSONField(name = "file_type")
    @TableField(value = "file_type")
    @ApiModelProperty(notes = "文件类型")
    private String fileType;

    @JSONField(name = "extension")
    @TableField(value = "extension")
    @ApiModelProperty(notes = "拓展名")
    private String extension;

    @JSONField(name = "create_time")
    @TableField(value = "create_time")
    @ApiModelProperty(notes = "创建时间")
    private Date createTime;

}

package com.example.scaiofficialwebsite.demos.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName projects
 */
@TableName(value ="projects")
@Data
public class Projects implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 描述
     */
    private String projectDescription;

    /**
     * 正文
     */
    private String projectContent;

    /**
     * 项目状态:准备中/可进行/已下马
     */
    private String projectStatus;

    /**
     * 项目类型:AI模型/网页设计/APP设计
     */
    private String projectType;

    /**
     * 编辑时间
     */
    private Date editTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
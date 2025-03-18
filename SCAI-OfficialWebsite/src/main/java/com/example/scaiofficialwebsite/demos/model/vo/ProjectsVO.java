package com.example.scaiofficialwebsite.demos.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-03-18
 * Time: 13:53
 */
@Data
public class ProjectsVO implements Serializable {

    private static final long serialVersionUID = -6907259246930698297L;

    /**
     * id
     */
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
     * 图片连接
     */
    private String imageUrl;

    /**
     * 项目连接
     */
    private String projectUrl;

    /**
     * 创建时间
     */
    private Date createTime;
}

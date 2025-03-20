package com.example.scaiofficialwebsite.demos.model.dto.project;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-03-18
 * Time: 13:56
 */
@Data
public class ProjectAddRequest implements Serializable {

    private static final long serialVersionUID = -3332575932954677388L;

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
     * 项目连接
     */
    private String projectUrl;
}

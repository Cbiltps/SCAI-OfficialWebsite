package com.example.scaiofficialwebsite.demos.model.dto.project;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-03-18
 * Time: 13:57
 */
@Data
public class ProjectUpdateRequest implements Serializable {

    private static final long serialVersionUID = 2680058067346407714L;

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
}

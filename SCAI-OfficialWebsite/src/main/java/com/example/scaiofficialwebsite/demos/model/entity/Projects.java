package com.example.scaiofficialwebsite.demos.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-03-14
 * Time: 15:51
 */
@Data
public class Projects {
    private Long id;
    private String projectName;
    private String projectDescription;
    private String projectContent;
    private Date startDate;
    private Date endDate;
    private String projectStatus;
}

package com.example.scaiofficialwebsite.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.scaiofficialwebsite.demos.model.dto.project.ProjectQueryRequest;
import com.example.scaiofficialwebsite.demos.model.entity.Projects;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.scaiofficialwebsite.demos.model.vo.ProjectsVO;

import java.util.List;

/**
* @author lichengxiang
* @description 针对表【projects】的数据库操作Service
* @createDate 2025-03-18 13:24:41
*/
public interface ProjectsService extends IService<Projects> {

    List<ProjectsVO> getProjectsVOList(List<Projects> records);

    QueryWrapper<Projects> getQueryWrapper(ProjectQueryRequest projectQueryRequest);

    ProjectsVO getProjectsVO(Projects projects);
}

package com.example.scaiofficialwebsite.demos.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scaiofficialwebsite.demos.exception.BusinessException;
import com.example.scaiofficialwebsite.demos.exception.ErrorCode;
import com.example.scaiofficialwebsite.demos.mapper.ProjectsMapper;
import com.example.scaiofficialwebsite.demos.model.dto.project.ProjectQueryRequest;
import com.example.scaiofficialwebsite.demos.model.entity.Projects;
import com.example.scaiofficialwebsite.demos.model.vo.ProjectsVO;
import com.example.scaiofficialwebsite.demos.service.ProjectsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author lichengxiang
* @description 针对表【projects】的数据库操作Service实现
* @createDate 2025-03-18 13:24:41
*/
@Service
public class ProjectsServiceImpl extends ServiceImpl<ProjectsMapper, Projects>
    implements ProjectsService {

    @Override
    public List<ProjectsVO> getProjectsVOList(List<Projects> records) {
        if (CollUtil.isEmpty(records)) {
            return new ArrayList<>();
        }
        return records.stream().map(this::getProjectsVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<Projects> getQueryWrapper(ProjectQueryRequest projectQueryRequest) {
        if (projectQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空!");
        }
        Long id = projectQueryRequest.getId();
        String projectName = projectQueryRequest.getProjectName();
        String projectDescription = projectQueryRequest.getProjectDescription();
        String projectContent = projectQueryRequest.getProjectContent();
        String projectStatus = projectQueryRequest.getProjectStatus();
        String projectType = projectQueryRequest.getProjectType();
        String sortField = projectQueryRequest.getSortField();
        String sortOrder = projectQueryRequest.getSortOrder();
        QueryWrapper<Projects> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjUtil.isNotNull(id), "id", id);
        queryWrapper.like(StrUtil.isNotBlank(projectName), "projectName", projectName);
        queryWrapper.like(StrUtil.isNotBlank(projectDescription), "projectDescription", projectDescription);
        queryWrapper.like(StrUtil.isNotBlank(projectContent), "projectContent", projectContent);
        queryWrapper.eq(StrUtil.isNotBlank(projectStatus), "projectStatus", projectStatus);
        queryWrapper.eq(StrUtil.isNotBlank(projectType), "projectType", projectType);
        queryWrapper.orderBy(StrUtil.isNotEmpty(sortField), sortOrder.equals("descend"), sortField);
        return queryWrapper;
    }

    @Override
    public ProjectsVO getProjectsVO(Projects projects) {
        if (projects == null) {
            return null;
        }
        ProjectsVO projectsVO = new ProjectsVO();
        BeanUtil.copyProperties(projects, projectsVO);
        return projectsVO;
    }
}





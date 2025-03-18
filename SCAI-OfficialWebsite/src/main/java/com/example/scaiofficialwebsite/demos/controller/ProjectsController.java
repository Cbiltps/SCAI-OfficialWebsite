package com.example.scaiofficialwebsite.demos.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.scaiofficialwebsite.demos.common.BaseResponse;
import com.example.scaiofficialwebsite.demos.common.ResultUtils;
import com.example.scaiofficialwebsite.demos.exception.BusinessException;
import com.example.scaiofficialwebsite.demos.exception.ErrorCode;
import com.example.scaiofficialwebsite.demos.exception.ThrowUtils;
import com.example.scaiofficialwebsite.demos.model.dto.project.ProjectAddRequest;
import com.example.scaiofficialwebsite.demos.model.dto.project.ProjectDeleteRequest;
import com.example.scaiofficialwebsite.demos.model.dto.project.ProjectQueryRequest;
import com.example.scaiofficialwebsite.demos.model.dto.project.ProjectUpdateRequest;
import com.example.scaiofficialwebsite.demos.model.entity.Projects;
import com.example.scaiofficialwebsite.demos.model.vo.ProjectsVO;
import com.example.scaiofficialwebsite.demos.service.ProjectsService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-03-18
 * Time: 13:30
 */
@RestController
@RequestMapping("/projects")
public class ProjectsController {
    @Resource
    ProjectsService projectsService;

    @PostMapping("/add")
    public BaseResponse<Long> addProjects(@RequestBody ProjectAddRequest projectAddRequest) {
        ThrowUtils.throwIf(projectAddRequest == null, ErrorCode.PARAMS_ERROR);
        Projects projects = new Projects();
        BeanUtil.copyProperties(projectAddRequest, projects);
        boolean result = projectsService.save(projects);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(projects.getId());
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteProjects(@RequestBody ProjectDeleteRequest projectDeleteRequest) {
        if (projectDeleteRequest == null || projectDeleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = projectsService.removeById(projectDeleteRequest.getId());
        return ResultUtils.success(result);
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updateProjects(@RequestBody ProjectUpdateRequest projectUpdateRequest) {
        if (projectUpdateRequest == null || projectUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Projects projects = new Projects();
        BeanUtils.copyProperties(projectUpdateRequest, projects);
        boolean result = projectsService.updateById(projects);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @GetMapping("/get")
    public BaseResponse<Projects> getProjectsById(long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        Projects projects = projectsService.getById(id);
        ThrowUtils.throwIf(projects == null, ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(projects);
    }

    /**
     * 分页获取新闻封装列表
     * @return
     */
    @PostMapping("/page")
    public BaseResponse<Page<ProjectsVO>> getProjectsVOListByPage(@RequestBody ProjectQueryRequest projectQueryRequest) {
        ThrowUtils.throwIf(projectQueryRequest == null, ErrorCode.PARAMS_ERROR);
        long current = projectQueryRequest.getCurrent();
        long pageSize = projectQueryRequest.getPageSize();
        Page<Projects> projectsPage = projectsService.page(new Page<>(current, pageSize), projectsService.getQueryWrapper(projectQueryRequest));
        Page<ProjectsVO> projectsVOPage = new Page<>(current, pageSize, projectsPage.getTotal());
        List<ProjectsVO> projectsVOList = projectsService.getProjectsVOList(projectsPage.getRecords());
        projectsVOPage.setRecords(projectsVOList);
        return ResultUtils.success(projectsVOPage);
    }
}

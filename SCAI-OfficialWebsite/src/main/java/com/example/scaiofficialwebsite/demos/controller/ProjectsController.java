package com.example.scaiofficialwebsite.demos.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.scaiofficialwebsite.demos.annotation.AuthCheck;
import com.example.scaiofficialwebsite.demos.common.BaseResponse;
import com.example.scaiofficialwebsite.demos.common.ResultUtils;
import com.example.scaiofficialwebsite.demos.constant.UserConstant;
import com.example.scaiofficialwebsite.demos.exception.BusinessException;
import com.example.scaiofficialwebsite.demos.exception.ErrorCode;
import com.example.scaiofficialwebsite.demos.exception.ThrowUtils;
import com.example.scaiofficialwebsite.demos.manager.FileManager;
import com.example.scaiofficialwebsite.demos.model.dto.project.ProjectAddRequest;
import com.example.scaiofficialwebsite.demos.model.dto.project.ProjectDeleteRequest;
import com.example.scaiofficialwebsite.demos.model.dto.project.ProjectQueryRequest;
import com.example.scaiofficialwebsite.demos.model.dto.project.ProjectUpdateRequest;
import com.example.scaiofficialwebsite.demos.model.entity.Projects;
import com.example.scaiofficialwebsite.demos.model.vo.ProjectsVO;
import com.example.scaiofficialwebsite.demos.service.ProjectsService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

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

    @Resource
    FileManager fileManager;

    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addProjects(@RequestPart("jsonData") String projectAddRequestJsonData,
                                       @RequestPart("file") MultipartFile multipartFile) {
        ProjectAddRequest projectAddRequest = JSONUtil.toBean(projectAddRequestJsonData, ProjectAddRequest.class);
        ThrowUtils.throwIf(projectAddRequest == null || multipartFile == null, ErrorCode.PARAMS_ERROR);
        Projects projects = new Projects();
        BeanUtil.copyProperties(projectAddRequest, projects);
        String savePath = fileManager.uploadFileToLocal(multipartFile);
        projects.setImageUrl(savePath);
        boolean result = projectsService.save(projects);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(projects.getId());
    }

    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteProjects(@RequestBody ProjectDeleteRequest projectDeleteRequest) {
        if (projectDeleteRequest == null || projectDeleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Projects oldProjects  = projectsService.getById(projectDeleteRequest.getId());
        ThrowUtils.throwIf(oldProjects == null, ErrorCode.NOT_FOUND_ERROR);
        fileManager.deleteLocalFile(oldProjects, Projects::getImageUrl);
        boolean result = projectsService.removeById(projectDeleteRequest.getId());
        return ResultUtils.success(result);
    }

    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateProjects(@RequestPart("jsonData") String projectUpdateRequestJsonData,
                                                @RequestPart("file") MultipartFile multipartFile) {
        ProjectUpdateRequest projectUpdateRequest = JSONUtil.toBean(projectUpdateRequestJsonData, ProjectUpdateRequest.class);
        if (projectUpdateRequest == null || projectUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Projects projects = new Projects();
        BeanUtils.copyProperties(projectUpdateRequest, projects);
        String oldPath = projectsService.getById(projects.getId()).getImageUrl();
        projects.setImageUrl(oldPath);
        fileManager.deleteLocalFile(projects, Projects::getImageUrl);
        String savePath = fileManager.uploadFileToLocal(multipartFile);
        projects.setImageUrl(savePath);
        boolean result = projectsService.updateById(projects);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @GetMapping("/get")
    public BaseResponse<?> getProjectsById(long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        Projects projects = projectsService.getById(id);
        ThrowUtils.throwIf(projects == null, ErrorCode.NOT_FOUND_ERROR);
        byte[] fileContent = fileManager.readFile(projects, Projects::getImageUrl);
        ThrowUtils.throwIf(fileContent == null, ErrorCode.PARAMS_ERROR, "文件内容为空!");
        ProjectsVO projectsVO = projectsService.getProjectsVO(projects);
        projectsVO.setFileContent(fileContent);
        return ResultUtils.success(projectsVO);
    }

    /**
     * 分页获取新闻封装列表
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<ProjectsVO>> getProjectsVOListByPage(@RequestBody ProjectQueryRequest projectQueryRequest) {
        ThrowUtils.throwIf(projectQueryRequest == null, ErrorCode.PARAMS_ERROR);
        long current = projectQueryRequest.getCurrent();
        long pageSize = projectQueryRequest.getPageSize();
        Page<Projects> projectsPage = projectsService.page(new Page<>(current, pageSize), projectsService.getQueryWrapper(projectQueryRequest));
        Page<ProjectsVO> projectsVOPage = new Page<>(current, pageSize, projectsPage.getTotal());
        List<ProjectsVO> projectsVOList = projectsService.getProjectsVOList(projectsPage.getRecords());
        // projectsVOList获取Project的id, 然后去allFileContentsMap里面找对应的内容,并赋值到list里面
        List<ProjectsVO> newProjectsVOList = fileManager.assignFileContentToList(projectsVOList, ProjectsVO::getImageUrl,
                ProjectsVO::getId, (projectVO, id, content) -> projectVO.setFileContent(content));
        projectsVOPage.setRecords(newProjectsVOList);
        return ResultUtils.success(projectsVOPage);
    }
}

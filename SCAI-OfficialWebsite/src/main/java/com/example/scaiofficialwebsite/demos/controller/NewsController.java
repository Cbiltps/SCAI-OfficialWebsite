package com.example.scaiofficialwebsite.demos.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSON;
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
import com.example.scaiofficialwebsite.demos.model.dto.news.NewsAddRequest;
import com.example.scaiofficialwebsite.demos.model.dto.news.NewsDeleteRequest;
import com.example.scaiofficialwebsite.demos.model.dto.news.NewsQueryRequest;
import com.example.scaiofficialwebsite.demos.model.dto.news.NewsUpdateRequest;
import com.example.scaiofficialwebsite.demos.model.entity.News;
import com.example.scaiofficialwebsite.demos.model.vo.NewsVO;
import com.example.scaiofficialwebsite.demos.service.NewsService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-03-17
 * Time: 11:47
 */
@RestController
@RequestMapping("/news")
public class NewsController {
    @Resource
    NewsService newsService;

    @Resource
    FileManager fileManager;

//    @PostMapping("/add")
//    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
//    public BaseResponse<Long> addNews(@RequestBody NewsAddRequest newsAddRequest,
//                                      @RequestPart("file") MultipartFile multipartFile) {
//        ThrowUtils.throwIf(newsAddRequest == null || multipartFile == null, ErrorCode.PARAMS_ERROR);
//        News news = new News();
//        BeanUtil.copyProperties(newsAddRequest, news);
//        String savePath = fileManager.uploadFileToLocal(multipartFile);
//        news.setNewsUrl(savePath);
//        boolean result = newsService.save(news);
//        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
//        return ResultUtils.success(news.getId());
//    }

    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> addNews(@RequestPart("jsonData") String newsAddRequestJsonData,
                                      @RequestPart("file") MultipartFile multipartFile) {
        NewsAddRequest newsAddRequest = JSONUtil.toBean(newsAddRequestJsonData, NewsAddRequest.class);
        ThrowUtils.throwIf(newsAddRequest == null || multipartFile == null, ErrorCode.PARAMS_ERROR);
        News news = new News();
        BeanUtil.copyProperties(newsAddRequest, news);
        String savePath = fileManager.uploadFileToLocal(multipartFile);
        news.setImageUrl(savePath);
        boolean result = newsService.save(news);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(news.getId());
    }

    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteNews(@RequestBody NewsDeleteRequest newsDeleteRequest) {
        if (newsDeleteRequest == null || newsDeleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        News oldNews = newsService.getById(newsDeleteRequest.getId());
        ThrowUtils.throwIf(oldNews == null, ErrorCode.NOT_FOUND_ERROR);
        fileManager.deleteLocalFile(oldNews, News::getImageUrl);
        boolean result = newsService.removeById(newsDeleteRequest.getId());
        return ResultUtils.success(result);
    }

    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateNews(@RequestPart("jsonData") String newUpdateRequestJsonData,
                                            @RequestPart("file") MultipartFile multipartFile) {
        NewsUpdateRequest newsUpdateRequest = JSONUtil.toBean(newUpdateRequestJsonData, NewsUpdateRequest.class);
        if (newsUpdateRequest == null || newsUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        News news = new News();
        BeanUtils.copyProperties(newsUpdateRequest, news);
        String oldPath = newsService.getById(news.getId()).getImageUrl();
        news.setImageUrl(oldPath);
        fileManager.deleteLocalFile(news, News::getImageUrl);
        String savePath = fileManager.uploadFileToLocal(multipartFile);
        news.setNewsUrl("News" + savePath);
        boolean result = newsService.updateById(news);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @GetMapping("/get")
    public BaseResponse<?> getNewsById(long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        News news = newsService.getById(id);
        ThrowUtils.throwIf(news == null, ErrorCode.NOT_FOUND_ERROR);
        byte[] fileContent = fileManager.readFile(news, News::getImageUrl);
        ThrowUtils.throwIf(fileContent == null, ErrorCode.PARAMS_ERROR, "文件内容为空!");
        NewsVO newsVO = newsService.getNewsVO(news);
        newsVO.setFileContent(fileContent);
        return ResultUtils.success(newsVO);
    }

    /**
     * 分页获取新闻封装列表
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<NewsVO>> getNewsVOListByPage(@RequestBody NewsQueryRequest newsQueryRequest) {
        ThrowUtils.throwIf(newsQueryRequest == null, ErrorCode.PARAMS_ERROR);
        long current = newsQueryRequest.getCurrent();
        long pageSize = newsQueryRequest.getPageSize();
        Page<News> newsPage = newsService.page(new Page<>(current, pageSize), newsService.getQueryWrapper(newsQueryRequest));
        Page<NewsVO> newsVOPage = new Page<>(current, pageSize, newsPage.getTotal());
        List<NewsVO> newsVOList = newsService.getNewsVOList(newsPage.getRecords());
        List<NewsVO> newNewsVOList = fileManager.assignFileContentToList(newsVOList, NewsVO::getImageUrl, NewsVO::getId,
                (newsVO, id, content) -> newsVO.setFileContent(content));
        newsVOPage.setRecords(newNewsVOList);
        return ResultUtils.success(newsVOPage);
    }
}

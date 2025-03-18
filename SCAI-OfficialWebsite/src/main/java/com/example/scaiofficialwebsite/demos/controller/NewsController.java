package com.example.scaiofficialwebsite.demos.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.scaiofficialwebsite.demos.common.BaseResponse;
import com.example.scaiofficialwebsite.demos.common.ResultUtils;
import com.example.scaiofficialwebsite.demos.exception.BusinessException;
import com.example.scaiofficialwebsite.demos.exception.ErrorCode;
import com.example.scaiofficialwebsite.demos.exception.ThrowUtils;
import com.example.scaiofficialwebsite.demos.model.dto.news.NewsAddRequest;
import com.example.scaiofficialwebsite.demos.model.dto.news.NewsDeleteRequest;
import com.example.scaiofficialwebsite.demos.model.dto.news.NewsQueryRequest;
import com.example.scaiofficialwebsite.demos.model.dto.news.NewsUpdateRequest;
import com.example.scaiofficialwebsite.demos.model.entity.News;
import com.example.scaiofficialwebsite.demos.model.vo.NewsVO;
import com.example.scaiofficialwebsite.demos.service.NewsService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public BaseResponse<Long> addNews(@RequestBody NewsAddRequest newsAddRequest) {
        ThrowUtils.throwIf(newsAddRequest == null, ErrorCode.PARAMS_ERROR);
        News news = new News();
        BeanUtil.copyProperties(newsAddRequest, news);
        boolean result = newsService.save(news);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(news.getId());
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteNews(@RequestBody NewsDeleteRequest newsDeleteRequest) {
        if (newsDeleteRequest == null || newsDeleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = newsService.removeById(newsDeleteRequest.getId());
        return ResultUtils.success(result);
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updateNews(@RequestBody NewsUpdateRequest newsUpdateRequest) {
        if (newsUpdateRequest == null || newsUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        News news = new News();
        BeanUtils.copyProperties(newsUpdateRequest, news);
        boolean result = newsService.updateById(news);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @GetMapping("/get")
    public BaseResponse<News> getNewsById(long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        News news = newsService.getById(id);
        ThrowUtils.throwIf(news == null, ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(news);
    }

    /**
     * 分页获取新闻封装列表
     * @return
     */
    @PostMapping("/page")
    public BaseResponse<Page<NewsVO>> getNewsVOListByPage(@RequestBody NewsQueryRequest newsQueryRequest) {
        ThrowUtils.throwIf(newsQueryRequest == null, ErrorCode.PARAMS_ERROR);
        long current = newsQueryRequest.getCurrent();
        long pageSize = newsQueryRequest.getPageSize();
        Page<News> newsPage = newsService.page(new Page<>(current, pageSize), newsService.getQueryWrapper(newsQueryRequest));
        Page<NewsVO> newsVOPage = new Page<>(current, pageSize, newsPage.getTotal());
        List<NewsVO> newsVOList = newsService.getNewsVOList(newsPage.getRecords());
        newsVOPage.setRecords(newsVOList);
        return ResultUtils.success(newsVOPage);
    }
}

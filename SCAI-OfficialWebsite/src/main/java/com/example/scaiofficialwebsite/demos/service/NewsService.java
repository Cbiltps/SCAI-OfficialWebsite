package com.example.scaiofficialwebsite.demos.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.scaiofficialwebsite.demos.model.dto.news.NewsQueryRequest;
import com.example.scaiofficialwebsite.demos.model.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.scaiofficialwebsite.demos.model.vo.NewsVO;

import java.util.List;

/**
* @author lichengxiang
* @description 针对表【news】的数据库操作Service
* @createDate 2025-03-17 15:07:00
*/
public interface NewsService extends IService<News> {

    /**
     * 获取查询条件
     * @param newsQueryRequest
     * @return
     */
    QueryWrapper<News> getQueryWrapper(NewsQueryRequest newsQueryRequest);

    /**
     * 获取新闻的VO列表
     * @param newsList
     * @return
     */
    List<NewsVO> getNewsVOList(List<News> newsList);

    /**
     * 获取新闻的VO对象
     * @param news
     * @return
     */
    NewsVO getNewsVO(News news);
}

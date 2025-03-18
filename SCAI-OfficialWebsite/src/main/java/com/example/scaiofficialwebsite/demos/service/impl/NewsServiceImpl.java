package com.example.scaiofficialwebsite.demos.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scaiofficialwebsite.demos.exception.BusinessException;
import com.example.scaiofficialwebsite.demos.exception.ErrorCode;
import com.example.scaiofficialwebsite.demos.mapper.NewsMapper;
import com.example.scaiofficialwebsite.demos.model.dto.news.NewsQueryRequest;
import com.example.scaiofficialwebsite.demos.model.entity.News;
import com.example.scaiofficialwebsite.demos.model.vo.NewsVO;
import com.example.scaiofficialwebsite.demos.service.NewsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author lichengxiang
* @description 针对表【news】的数据库操作Service实现
* @createDate 2025-03-17 15:07:00
*/
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News>
    implements NewsService {

    @Override
    public QueryWrapper<News> getQueryWrapper(NewsQueryRequest newsQueryRequest) {
        if (newsQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空!");
        }
        Long id = newsQueryRequest.getId();
        String title = newsQueryRequest.getTitle();
        String newsDescription = newsQueryRequest.getNewsDescription();
        String newsContent = newsQueryRequest.getNewsContent();
        String newsType = newsQueryRequest.getNewsType();
        String author = newsQueryRequest.getAuthor();
        String sortField = newsQueryRequest.getSortField();
        String sortOrder = newsQueryRequest.getSortOrder();
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjUtil.isNotNull(id), "id", id);
        queryWrapper.like(StrUtil.isNotBlank(title), "title", title);
        queryWrapper.like(StrUtil.isNotBlank(newsDescription), "newsDescription", newsDescription);
        queryWrapper.like(StrUtil.isNotBlank(newsContent), "newsContent", newsContent);
        queryWrapper.eq(StrUtil.isNotBlank(newsType), "newsType", newsType);
        queryWrapper.like(StrUtil.isNotBlank(author), "author", author);
        queryWrapper.orderBy(StrUtil.isNotEmpty(sortField), sortOrder.equals("descend"), sortField);
        return queryWrapper;
    }

    @Override
    public List<NewsVO> getNewsVOList(List<News> records) {
        if (CollUtil.isEmpty(records)) {
            return new ArrayList<>();
        }
        return records.stream().map(this::getNewsVO).collect(Collectors.toList());
    }

    @Override
    public NewsVO getNewsVO(News news) {
        if (news == null) {
            return null;
        }
        NewsVO newsVO = new NewsVO();
        BeanUtil.copyProperties(news, newsVO);
        return newsVO;
    }

}





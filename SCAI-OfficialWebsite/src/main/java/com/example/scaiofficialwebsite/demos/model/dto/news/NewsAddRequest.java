package com.example.scaiofficialwebsite.demos.model.dto.news;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-03-17
 * Time: 15:34
 */
@Data
public class NewsAddRequest implements Serializable {

    private static final long serialVersionUID = 887856485383957985L;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String newsDescription;

    /**
     * 正文
     */
    private String newsContent;

    /**
     * 类型
     */
    private String newsType;

    /**
     * 作者
     */
    private String author;

    /**
     * 新闻连接
     */
    private String newsUrl;
}

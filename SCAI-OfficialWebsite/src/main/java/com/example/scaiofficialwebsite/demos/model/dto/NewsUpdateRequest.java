package com.example.scaiofficialwebsite.demos.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-03-17
 * Time: 16:05
 */
@Data
public class NewsUpdateRequest implements Serializable {

    private static final long serialVersionUID = -8856784440146500943L;

    /**
     * id
     */
    private Long id;

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
     * 图片链接
     */
    private String imageUrl;

    /**
     * 新闻连接
     */
    private String newsUrl;
}

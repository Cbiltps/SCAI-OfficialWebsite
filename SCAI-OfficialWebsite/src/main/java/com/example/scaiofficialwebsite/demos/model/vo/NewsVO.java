package com.example.scaiofficialwebsite.demos.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-03-17
 * Time: 16:32
 */
@Data
public class NewsVO implements Serializable {

    private static final long serialVersionUID = 6908154394548835127L;

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

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 图片内容
     */
    private byte[] fileContent;
}

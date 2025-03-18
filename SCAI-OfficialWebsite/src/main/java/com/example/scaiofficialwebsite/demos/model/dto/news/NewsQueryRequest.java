package com.example.scaiofficialwebsite.demos.model.dto.news;

import com.example.scaiofficialwebsite.demos.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-03-17
 * Time: 16:36
 */
@Data
public class NewsQueryRequest extends PageRequest implements Serializable {

    private static final long serialVersionUID = 2203230634190305087L;

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
}

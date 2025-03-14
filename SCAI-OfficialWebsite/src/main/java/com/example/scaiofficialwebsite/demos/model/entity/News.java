package com.example.scaiofficialwebsite.demos.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-03-14
 * Time: 15:51
 */
@Data
public class News {
    private Long id;
    private String title;
    private String newDescription;
    private String newContent;
    private Date publishTime;
    private String author;
    private String imageUrl;
}

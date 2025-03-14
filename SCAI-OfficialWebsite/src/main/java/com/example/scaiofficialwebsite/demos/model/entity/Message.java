package com.example.scaiofficialwebsite.demos.model.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 绝版龙宝宝
 * Date: 2025-03-13
 * Time: 18:18
 */
@Data
public class Message {
    private Long id;
    private String content;
    private Date createTime;
}

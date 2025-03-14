package com.example.scaiofficialwebsite.demos.repository;

import com.example.scaiofficialwebsite.demos.model.entity.Message;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 绝版龙宝宝
 * Date: 2025-03-13
 * Time: 18:24
 */
public interface MessageRepository {
    Message save(Message message);

    List<Message> findAll();
}

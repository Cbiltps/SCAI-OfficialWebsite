package com.example.scaiofficialwebsite.demos.service;

import com.example.scaiofficialwebsite.demos.model.entity.Message;
import com.example.scaiofficialwebsite.demos.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 绝版龙宝宝
 * Date: 2025-03-13
 * Time: 18:21
 */
@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;

    public Message saveMessage(String content) {
        Message message = new Message();
        message.setContent(content);
        message.setCreateTime(new Date());
        return messageMapper.save(message);
    }

    public List<Message> getAllMessages() {
        return messageMapper.findAll();
    }
}

package com.example.scaiofficialwebsite.demos.controller;

import com.example.scaiofficialwebsite.demos.model.entity.Message;
import com.example.scaiofficialwebsite.demos.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 绝版龙宝宝
 * Date: 2025-03-13
 * Time: 18:20
 */
@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    public Message saveMessage(@RequestBody String content) {
        return messageService.saveMessage(content);
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }
}

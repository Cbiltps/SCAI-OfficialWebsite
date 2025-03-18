package com.example.scaiofficialwebsite.demos.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.scaiofficialwebsite.demos.model.dto.message.MessageQueryRequest;
import com.example.scaiofficialwebsite.demos.model.entity.Message;
import com.example.scaiofficialwebsite.demos.model.vo.MessageVO;

import java.util.List;

/**
* @author lichengxiang
* @description 针对表【message】的数据库操作Service
* @createDate 2025-03-18 15:18:06
*/
public interface MessageService extends IService<Message> {

    List<MessageVO> getMessageVOList(List<Message> records);

    QueryWrapper<Message> getQueryWrapper(MessageQueryRequest messageQueryRequest);

    MessageVO getMessageVO(Message message);
}

package com.example.scaiofficialwebsite.demos.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.scaiofficialwebsite.demos.exception.BusinessException;
import com.example.scaiofficialwebsite.demos.exception.ErrorCode;
import com.example.scaiofficialwebsite.demos.mapper.MessageMapper;
import com.example.scaiofficialwebsite.demos.model.dto.message.MessageQueryRequest;
import com.example.scaiofficialwebsite.demos.model.entity.Message;
import com.example.scaiofficialwebsite.demos.model.vo.MessageVO;
import com.example.scaiofficialwebsite.demos.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author lichengxiang
* @description 针对表【message】的数据库操作Service实现
* @createDate 2025-03-18 15:18:06
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService {

    @Override
    public List<MessageVO> getMessageVOList(List<Message> records) {
        if (CollUtil.isEmpty(records)) {
            return new ArrayList<>();
        }
        return records.stream().map(this::getMessageVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<Message> getQueryWrapper(MessageQueryRequest messageQueryRequest) {
        if (messageQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空!");
        }
        Long id = messageQueryRequest.getId();
        String userName = messageQueryRequest.getUserName();
        String userPhone = messageQueryRequest.getUserPhone();
        String userEmail = messageQueryRequest.getUserEmail();
        String messageContent = messageQueryRequest.getMessageContent();
        String sortField = messageQueryRequest.getSortField();
        String sortOrder = messageQueryRequest.getSortOrder();
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjUtil.isNotNull(id), "id", id);
        queryWrapper.like(StrUtil.isNotBlank(userName), "userName", userName);
        queryWrapper.like(StrUtil.isNotBlank(userPhone), "userPhone", userPhone);
        queryWrapper.like(StrUtil.isNotBlank(userEmail), "userEmail", userEmail);
        queryWrapper.like(StrUtil.isNotBlank(messageContent), "messageContent", messageContent);
        queryWrapper.orderBy(StrUtil.isNotEmpty(sortField), sortOrder.equals("descend"), sortField);
        return queryWrapper;
    }

    @Override
    public MessageVO getMessageVO(Message message) {
        if (message == null) {
            return null;
        }
        MessageVO messageVO = new MessageVO();
        BeanUtil.copyProperties(message, messageVO);
        return messageVO;
    }
}





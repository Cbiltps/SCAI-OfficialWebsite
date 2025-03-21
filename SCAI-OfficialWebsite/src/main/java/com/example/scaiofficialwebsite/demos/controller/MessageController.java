package com.example.scaiofficialwebsite.demos.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.scaiofficialwebsite.demos.common.BaseResponse;
import com.example.scaiofficialwebsite.demos.common.ResultUtils;
import com.example.scaiofficialwebsite.demos.exception.BusinessException;
import com.example.scaiofficialwebsite.demos.exception.ErrorCode;
import com.example.scaiofficialwebsite.demos.exception.ThrowUtils;
import com.example.scaiofficialwebsite.demos.model.dto.message.MessageAddRequest;
import com.example.scaiofficialwebsite.demos.model.dto.message.MessageDeleteRequest;
import com.example.scaiofficialwebsite.demos.model.dto.message.MessageQueryRequest;
import com.example.scaiofficialwebsite.demos.model.dto.message.MessageUpdateRequest;
import com.example.scaiofficialwebsite.demos.model.entity.Message;
import com.example.scaiofficialwebsite.demos.model.vo.MessageVO;
import com.example.scaiofficialwebsite.demos.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-03-18
 * Time: 15:23
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    MessageService messageService;

    @PostMapping("/add")
    public BaseResponse<Long> addMessage(@RequestBody MessageAddRequest messageAddRequest) {
        ThrowUtils.throwIf(messageAddRequest == null, ErrorCode.PARAMS_ERROR);
        Message message = new Message();
        BeanUtil.copyProperties(messageAddRequest, message);
        boolean result = messageService.save(message);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(message.getId());
    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteMessage(@RequestBody MessageDeleteRequest messageDeleteRequest) {
        if (messageDeleteRequest == null || messageDeleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean result = messageService.removeById(messageDeleteRequest.getId());
        return ResultUtils.success(result);
    }

    @PostMapping("/update")
    public BaseResponse<Boolean> updateMessage(@RequestBody MessageUpdateRequest messageUpdateRequest) {
        if (messageUpdateRequest == null || messageUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Message message = new Message();
        BeanUtils.copyProperties(messageUpdateRequest, message);
        boolean result = messageService.updateById(message);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @GetMapping("/get")
    public BaseResponse<Message> getMessageById(long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        Message message = messageService.getById(id);
        ThrowUtils.throwIf(message == null, ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(message);
    }

    /**
     * 分页获取新闻封装列表
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<MessageVO>> getMessageVOListByPage(@RequestBody MessageQueryRequest messageQueryRequest) {
        ThrowUtils.throwIf(messageQueryRequest == null, ErrorCode.PARAMS_ERROR);
        long current = messageQueryRequest.getCurrent();
        long pageSize = messageQueryRequest.getPageSize();
        Page<Message> messagePage = messageService.page(new Page<>(current, pageSize), messageService.getQueryWrapper(messageQueryRequest));
        Page<MessageVO> messageVOPage = new Page<>(current, pageSize, messagePage.getTotal());
        List<MessageVO> messageVOList = messageService.getMessageVOList(messagePage.getRecords());
        messageVOPage.setRecords(messageVOList);
        return ResultUtils.success(messageVOPage);
    }
}

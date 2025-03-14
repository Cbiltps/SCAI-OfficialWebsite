package com.example.scaiofficialwebsite.demos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.scaiofficialwebsite.demos.model.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 绝版龙宝宝
 * Date: 2025-03-13
 * Time: 18:24
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

    Message save(Message message);

    List<Message> findAll();
}

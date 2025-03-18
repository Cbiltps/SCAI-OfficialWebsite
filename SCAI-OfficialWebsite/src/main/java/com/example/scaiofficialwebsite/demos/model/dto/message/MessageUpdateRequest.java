package com.example.scaiofficialwebsite.demos.model.dto.message;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-03-18
 * Time: 15:28
 */
@Data
public class MessageUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1948995814818110687L;

    /**
     * id
     */
    private Long id;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户电话
     */
    private String userPhone;

    /**
     * 用户邮件
     */
    private String userEmail;

    /**
     * 留言正文
     */
    private String messageContent;
}

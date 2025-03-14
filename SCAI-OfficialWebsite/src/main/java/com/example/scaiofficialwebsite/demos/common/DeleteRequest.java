package com.example.scaiofficialwebsite.demos.common;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description: 通用的删除请求类
 * User: lichengxiang
 * Date: 2025-03-14
 * Time: 10:05
 */
@Data
public class DeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}

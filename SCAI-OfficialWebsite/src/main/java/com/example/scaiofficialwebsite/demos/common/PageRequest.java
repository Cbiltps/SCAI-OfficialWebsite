package com.example.scaiofficialwebsite.demos.common;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description: 通用的分页请求类
 * User: lichengxiang
 * Date: 2025-03-14
 * Time: 10:04
 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private int current = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = "descend";
}

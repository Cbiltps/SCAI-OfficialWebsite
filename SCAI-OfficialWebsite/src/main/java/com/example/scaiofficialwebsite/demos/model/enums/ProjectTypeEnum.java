package com.example.scaiofficialwebsite.demos.model.enums;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lichengxiang
 * Date: 2025-03-18
 * Time: 13:38
 */
@Getter
public enum ProjectTypeEnum {
    AI_MODEL("AI模型", "ai_model"),
    WEB_DESIGN("网页设计", "web_design"),
    APP_DESIGN("APP设计", "app_design");

    private final String text;

    private final String value;

    ProjectTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public static ProjectTypeEnum getEnumByValue(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        Map<String, ProjectTypeEnum> projectTypeEnumMap = Arrays.stream(ProjectTypeEnum.values())
                .collect(Collectors.toMap(ProjectTypeEnum::getValue, projectTypeEnum -> projectTypeEnum));
        return projectTypeEnumMap.getOrDefault(value, null);
    }
}

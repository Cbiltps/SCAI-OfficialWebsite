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
 * Time: 10:46
 */
@Getter
public enum ProjectStatusEnum {
    READY("准备中", "ready"),
    RUN("可进行", "run"),
    STOP("已下马", "stop");

    private final String text;

    private final String value;

    ProjectStatusEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public static ProjectStatusEnum getEnumByValue(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        Map<String, ProjectStatusEnum> projectStatusEnumMap = Arrays.stream(ProjectStatusEnum.values()).
                collect(Collectors.toMap(ProjectStatusEnum::getValue, projectStatusEnum -> projectStatusEnum));
        return projectStatusEnumMap.getOrDefault(value, null);
    }
}

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
 * Time: 10:26
 */
@Getter
public enum NewsTypeEnum {
    COMPANY_NEWS("公司新闻", "company_news"),
    INDUSTRY_NEWS("行业新闻", "industry_news");

    private final String text;

    private final String value;

    NewsTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public static NewsTypeEnum getEnumByValue(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        Map<String, NewsTypeEnum> newsTypeEnumMap = Arrays.stream(NewsTypeEnum.values()).
                collect(Collectors.toMap(NewsTypeEnum::getValue, newsTypeEnum -> newsTypeEnum));
        return newsTypeEnumMap.getOrDefault(value, null);
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value 枚举值的 value
     * @return 枚举值
     */
//    public static UserRoleEnum getEnumByValue(String value) {
//        if (ObjUtil.isEmpty(value)) {
//            return null;
//        }
//        for (UserRoleEnum userRoleEnum : UserRoleEnum.values()) {
//            if (userRoleEnum.value.equals(value)) {
//                return userRoleEnum;
//            }
//        }
//        return null;
//    }

}

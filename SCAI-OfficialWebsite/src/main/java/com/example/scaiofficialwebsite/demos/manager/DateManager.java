package com.example.scaiofficialwebsite.demos.manager;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.TriConsumer;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Created with IntelliJ IDEA.
 * Description: 前端需要时间戳, so, 写了下面的方法
 * User: lichengxiang
 * Date: 2025-03-24
 * Time: 10:54
 */
@Service
@Slf4j
public class DateManager {

    public long convertDateToTimestamp(Date date) {
        if (date == null) {
            return 0;
        }
//        System.out.println(date);
//        System.out.println(date.getTime());
//        // 将时间戳转换为 Instant 对象
//        Instant instant = Instant.ofEpochMilli(date.getTime());
//
//        // 将 Instant 对象转换为 LocalDateTime 对象，可指定时区
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
//
//        // 格式化输出
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String formattedDate = localDateTime.format(formatter);
//
//        System.out.println(formattedDate);
        return date.getTime();
    }

    public <T> Map<Long, Long> convertDateToMap(List<T> params, Function<T, Date> dateExtractor, Function<T, Long> idExtractor) {
        Map<Long, Long> allDateMap = new HashMap<>();
        for (T param : params) {
            Date standardDate = dateExtractor.apply(param);
            Long timestamp = convertDateToTimestamp(standardDate);
            Long id = idExtractor.apply(param);
            allDateMap.put(id, timestamp);
        }
        return allDateMap;
    }

    public <T> List<T> assignDateToList(Map<Long, Long> allDateMap, List<T> list, Function<T, Long> dateExtractor, Function<T, Long> idExtractor, TriConsumer<T, Long, Long> contentSetter) {

        for (T item : list) {
            Long id = idExtractor.apply(item);
            Long timestamp = allDateMap.get(id);
            if (timestamp != null) {
                contentSetter.accept(item, id, timestamp);
            }
        }
        return list;
    }
}

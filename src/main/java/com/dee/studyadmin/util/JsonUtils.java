package com.dee.studyadmin.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtils {
    public static String ObjToStr(Object object) {
        String str = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            str = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("对象转Json字符串失败：{}", e);
        }
        return str;
    }
}

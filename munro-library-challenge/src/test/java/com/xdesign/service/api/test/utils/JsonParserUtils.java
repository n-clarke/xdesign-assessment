package com.xdesign.service.api.test.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParserUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T fromJson(String jsonFile, TypeReference<T> type) {
        try {
            return objectMapper.readValue(jsonFile, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T fromJson(String jsonFile, Class<T> type) {
        try {
            return objectMapper.readValue(jsonFile, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

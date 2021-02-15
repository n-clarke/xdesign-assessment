package com.xdesign.service.api.test.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParserUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * A generic method to generate object based on the json file
     * using type reference
     *
     * @param jsonFile for a given json file
     * @param type     for a given TypeReference
     * @return {@linkplain <T>}
     */
    public static <T> T fromJson(String jsonFile, TypeReference<T> type) {
        try {
            return objectMapper.readValue(jsonFile, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * A generic method to generate object based on the json file
     * using Class
     *
     * @param jsonFile for a given json file
     * @param type     for a given class
     * @return {@linkplain <T>}
     */
    public static <T> T fromJson(String jsonFile, Class<T> type) {
        try {
            return objectMapper.readValue(jsonFile, type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}

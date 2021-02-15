package com.xdesign.service.api.test.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReaderUtil {

    /**
     * todo : description
     *
     * @param jsonFile
     * @return {@linkplain String}
     */
    public static String readJSONResponseFromFile(String jsonFile) {
        StringBuilder jsonStringBuilder = new StringBuilder();

        try {
            ClassPathResource classPathResource = new ClassPathResource(jsonFile);
            InputStream inputStream = classPathResource.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonStringBuilder.toString();
    }
}

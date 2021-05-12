package com.parsdeveloper.shopping.model.commons.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class JsonUtil {

    public static String objectToJson(Object object) {

        ObjectMapper Obj = new ObjectMapper();
        try {
            return Obj.writeValueAsString(object);
        } catch (IOException e) {
            return null;
        }
    }

    private Object jsonToObject(String responseText, Object object) {
        if (StringUtils.hasText(responseText)) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(responseText, object.getClass());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return object;
    }
}

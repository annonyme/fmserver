package de.hannespries.fm.utils;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONToolkit {
    public static Object jsonToObject(String json, Class<?> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }

}

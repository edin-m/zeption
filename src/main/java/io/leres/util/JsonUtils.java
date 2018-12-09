package io.leres.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonUtils {

    public static <T> List<T> fromJsonArray(String json) {
        return fromJson(new TypeReference<List<T>>() { }, json);
    }

    public static <T> T fromJson(TypeReference<T> type,
                                 String jsonPacket) {
        T data = null;

        try {
            data = new ObjectMapper().readValue(jsonPacket, type);
        } catch (Exception e) {
            data = null;
        }

        return data;
    }

}

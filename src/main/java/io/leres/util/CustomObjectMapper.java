package io.leres.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

@Component
public class CustomObjectMapper extends ObjectMapper {

    public static ObjectMapper OBJECT_MAPPER = new CustomObjectMapper();

    public CustomObjectMapper() {
        registerModule(new Jdk8Module());
        registerModule(new JavaTimeModule());
        disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}

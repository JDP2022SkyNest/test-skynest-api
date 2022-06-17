package com.skynest.tests.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTransformer {
    private static final ObjectMapper OBJECT_MAPPER= new ObjectMapper();
    public static String objectToJson(Object payload) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(payload);
    }
}

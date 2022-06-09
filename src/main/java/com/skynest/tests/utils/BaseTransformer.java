package com.skynest.tests.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTransformer {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object payload) throws JsonProcessingException {
        //Converting object to json, exported in Base Class for multiple usage
        String jsonPayload = objectMapper.writeValueAsString(payload);
        return jsonPayload;
    }
}

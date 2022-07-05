package com.skynest.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonTransformer {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    //Generic method that will work for any type of class that needs to be returned
    public static <T> T mapResponse(Response response, Class<T> responseClass) throws IOException {
        ResponseBody responseBody = response.body();
        return OBJECT_MAPPER.readValue(responseBody.print(), responseClass);
    }

    public static <T> List<T> mapResponseToList(Response response, Class<T> responseClass) throws IOException {
        ResponseBody responseBody = response.body();
        return OBJECT_MAPPER.readValue(responseBody.print(), OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, responseClass));
    }
}

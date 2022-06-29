package com.skynest.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skynest.models.LoggedUserResponse;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonTransformer {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static LoggedUserResponse mapResponseToLoggedUserResponse(Response response) throws IOException {
        ResponseBody responseBody = response.body();
        return OBJECT_MAPPER.readValue(responseBody.print(), LoggedUserResponse.class);
    }
}

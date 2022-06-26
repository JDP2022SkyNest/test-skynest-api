package com.skynest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skynest.models.LoginRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

import static org.apache.http.HttpStatus.SC_OK;

public class LoginTest extends BaseTest {

    public LoginTest() throws URISyntaxException {
    }

    @Test
    void valid_login_test() throws JsonProcessingException {
        LoginRequest body = new LoginRequest("christinezahar@yahoo.com", "Hris8833");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_OK);
    }
}

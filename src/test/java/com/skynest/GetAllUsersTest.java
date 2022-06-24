package com.skynest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skynest.clients.SkyNestBackendClient;
import com.skynest.files.LoginRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

import static org.apache.http.HttpStatus.SC_OK;

public class GetAllUsersTest {
    private SkyNestBackendClient skyNestBackendClient = new SkyNestBackendClient();

    public GetAllUsersTest() throws URISyntaxException {
    }

    @Test
    void valid_get_all_users_test() throws JsonProcessingException {
        LoginRequest body = new LoginRequest();
        body.setEmail("christinezahar@yahoo.com");
        body.setPassword("Hris8833");
        Response response = skyNestBackendClient.login(body);
        skyNestBackendClient.getAllUsers();
        response.then().statusCode(SC_OK);
        //skyNestBackendClient.login(body);
    }

}

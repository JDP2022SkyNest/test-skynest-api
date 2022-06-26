package com.skynest;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

import static org.apache.http.HttpStatus.SC_OK;

public class GetAllUsersTest extends LoggedUserBaseTest {

    public GetAllUsersTest() throws URISyntaxException {
    }

    @Test
    void valid_get_all_users_test() throws JsonProcessingException {
        Response response = skyNestBackendClient.getAllUsers();
        response.then().statusCode(SC_OK);
    }

}

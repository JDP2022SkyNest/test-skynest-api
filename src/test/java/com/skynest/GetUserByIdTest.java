package com.skynest;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.apache.http.HttpStatus.SC_OK;

public class GetUserByIdTest extends LoggedUserBaseTest {
    @Test
    void valid_get_user_by_id_test() throws IOException {
        Response response = skyNestBackendClient.getUserById();
        response.then().statusCode(SC_OK);
    }
}

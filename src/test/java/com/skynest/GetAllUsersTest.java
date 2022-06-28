package com.skynest;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;

public class GetAllUsersTest extends LoggedUserBaseTest {

    @Test
    void valid_get_all_users_test() {
        Response response = skyNestBackendClient.getAllUsers();
        response.then().statusCode(SC_OK);
    }

}

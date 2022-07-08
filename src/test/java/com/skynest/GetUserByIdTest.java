package com.skynest;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;

public class GetUserByIdTest extends BaseTest {
    @Test
    void get_worker_details_as_worker() throws IOException {
        loginAs(Roles.WORKER);
        UUID loggedUserId = getLoggedUserId();
        Response getUserResponse = skyNestBackendClient.getUserById(loggedUserId);
        getUserResponse.then().statusCode(SC_OK);
    }

    @Test
    void get_admin_details_as_admin() throws IOException {
        loginAs(Roles.ADMIN);
        UUID loggedUserId = getLoggedUserId();
        Response getUserResponse = skyNestBackendClient.getUserById(loggedUserId);
        getUserResponse.then().statusCode(SC_OK);
    }

}

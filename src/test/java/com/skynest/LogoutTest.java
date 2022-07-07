package com.skynest;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class LogoutTest extends BaseTest {

    @Test
    void logged_in_worker_can_logout_and_after_logout_token_is_invalidated() {
        loginAs(Roles.WORKER);
        Response logoutResponse = skyNestBackendClient.logout();
        logoutResponse.then().statusCode(SC_OK);
        Response loggedUserResponse = skyNestBackendClient.getLoggedUser();
        loggedUserResponse.then().statusCode(SC_UNAUTHORIZED);
    }

    @Test
    void logged_in_admin_can_logout_and_after_logout_token_is_invalidated() {
        loginAs(Roles.ADMIN);
        Response logoutResponse = skyNestBackendClient.logout();
        logoutResponse.then().statusCode(SC_OK);
        Response loggedUserResponse = skyNestBackendClient.getLoggedUser();
        loggedUserResponse.then().statusCode(SC_UNAUTHORIZED);
    }
}

package com.skynest;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class LogoutTest extends LoggedUserBaseTest {

    @Test
    void valid_logout_user_test_and_check_response_for_invalidated_token() {
        Response logoutResponse = skyNestBackendClient.logout();
        logoutResponse.then().statusCode(SC_OK);
        Response loggedUserResponse = skyNestBackendClient.getLoggedUser();
        loggedUserResponse.then().statusCode(SC_UNAUTHORIZED);
    }
}

package com.skynest;

import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class LogoutTest extends BaseTest {

    @Test(dataProvider = "loginAsWorkerOrAdmin")
    void logged_in_user_can_logout_and_after_logout_token_is_invalidated(Roles role) {
        loginAs(role);
        skyNestBackendClient.logout().then().statusCode(SC_OK);
        skyNestBackendClient.getLoggedUser().then().statusCode(SC_UNAUTHORIZED);
    }

}

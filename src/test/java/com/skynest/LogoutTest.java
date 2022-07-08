package com.skynest;

import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class LogoutTest extends BaseTest {

    @Test
    void logged_in_worker_can_logout_and_after_logout_token_is_invalidated() {
        loginAs(Roles.WORKER);
        skyNestBackendClient.logout().then().statusCode(SC_OK);
        skyNestBackendClient.getLoggedUser().then().statusCode(SC_UNAUTHORIZED);
    }

    @Test
    void logged_in_admin_can_logout_and_after_logout_token_is_invalidated() {
        loginAs(Roles.ADMIN);
        skyNestBackendClient.logout().then().statusCode(SC_OK);
        skyNestBackendClient.getLoggedUser().then().statusCode(SC_UNAUTHORIZED);
    }
}

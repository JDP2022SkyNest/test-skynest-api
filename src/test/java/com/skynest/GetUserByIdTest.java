package com.skynest;

import org.testng.annotations.Test;

import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;

public class GetUserByIdTest extends BaseTest {
    @Test
    void get_worker_details_as_worker() {
        loginAs(Roles.WORKER);
        UUID loggedUserId = getLoggedUserId();
        skyNestBackendClient.getUserById(loggedUserId).then().statusCode(SC_OK);
    }

    @Test
    void get_admin_details_as_admin() {
        loginAs(Roles.ADMIN);
        UUID loggedUserId = getLoggedUserId();
        skyNestBackendClient.getUserById(loggedUserId).then().statusCode(SC_OK);
    }

}

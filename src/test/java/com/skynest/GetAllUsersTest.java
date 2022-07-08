package com.skynest;

import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;

public class GetAllUsersTest extends BaseTest {

    @Test
    void get_all_users_as_admin() {
        loginAs(Roles.ADMIN);
        skyNestBackendClient.getAllUsers().then().statusCode(SC_OK);
    }

    @Test
    void get_all_users_as_worker() {
        loginAs(Roles.WORKER);
        skyNestBackendClient.getAllUsers().then().statusCode(SC_FORBIDDEN);
    }

}

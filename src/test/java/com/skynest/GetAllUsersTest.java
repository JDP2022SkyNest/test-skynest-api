package com.skynest;

import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;

public class GetAllUsersTest extends BaseTest {

    @Test
    void logged_admin_should_be_able_to_view_details_of_all_users() {
        loginAs(Roles.ADMIN);
        skyNestBackendClient.getAllUsers().then().statusCode(SC_OK);
    }

    @Test
    void logged_worker_should_not_be_able_to_view_details_of_all_users() {
        loginAs(Roles.WORKER);
        skyNestBackendClient.getAllUsers().then().statusCode(SC_FORBIDDEN);
    }

}

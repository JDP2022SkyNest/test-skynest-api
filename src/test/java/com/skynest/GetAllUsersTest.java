package com.skynest;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.*;

public class GetAllUsersTest extends BaseTest {

    @Test
    void get_all_users_as_admin() {
        loginAs(Roles.ADMIN);
        Response getAllUsersResponse = skyNestBackendClient.getAllUsers();
        getAllUsersResponse.then().statusCode(SC_OK);
    }

    @Test
    void get_all_users_as_worker() {
        loginAs(Roles.WORKER);
        Response getAllUsersResponse = skyNestBackendClient.getAllUsers();
        getAllUsersResponse.then().statusCode(SC_FORBIDDEN);
    }

}

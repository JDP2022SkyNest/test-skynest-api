package com.skynest;

import com.skynest.models.UserResponse;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;

/**
 * Class that contains tests for enable and disable user
 */
public class ModifyUserAccessAsAdminTest extends BaseTest {
    private UUID specificWorkerId;

    @BeforeClass
    void getWorkerId() {
        loginAs(Roles.ADMIN);
        Response getAllUsersResponse = skyNestBackendClient.getAllUsers();
        getAllUsersResponse.then().statusCode(SC_OK);
        UserResponse[] userResponses = getAllUsersResponse.body().as(UserResponse[].class);
        specificWorkerId = getSpecificWorkerId(List.of(userResponses));
    }

    @Test
    void admin_should_be_able_to_disable_worker() {
        skyNestBackendClient.disableUser(specificWorkerId).then().statusCode(SC_OK);
    }

    @AfterClass
    void admin_should_be_able_to_enable_disabled_worker() {
        skyNestBackendClient.enableUser(specificWorkerId).then().statusCode(SC_OK);
    }
}



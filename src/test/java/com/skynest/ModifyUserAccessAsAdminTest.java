package com.skynest;

import com.skynest.models.UserResponse;
import com.skynest.utils.JsonTransformer;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;

/**
 * Class that contains tests for enable and disable user
 */
public class ModifyUserAccessAsAdminTest extends BaseTest {
    private UUID workerId;

    @BeforeClass
    void getWorkerId() throws IOException {
        loginAs(Roles.ADMIN);
        Response getAllUsersResponse = skyNestBackendClient.getAllUsers();
        getAllUsersResponse.then().statusCode(SC_OK);

        List<UserResponse> userResponses = JsonTransformer.mapResponseToList(getAllUsersResponse, UserResponse.class);
        workerId = getSpecificWorkerId(userResponses);
    }

    @Test
    void disable_worker_as_admin_test() {
        skyNestBackendClient.disableUser(workerId).then().statusCode(SC_OK);
    }

    @AfterClass
    void enable_disabled_worker_as_admin_test() {
        skyNestBackendClient.enableUser(workerId).then().statusCode(SC_OK);
    }
}



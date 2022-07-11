package com.skynest;

import com.skynest.constants.Credentials;
import com.skynest.models.UserResponse;
import com.skynest.utils.JsonTransformer;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;

public class PromoteDemoteUserTest extends BaseTest {
    private UUID specificWorkerId;

    @BeforeClass
    void getWorkerId() throws IOException {
        loginAs(Roles.ADMIN);
        Response getAllUsersResponse = skyNestBackendClient.getAllUsers();
        getAllUsersResponse.then().statusCode(SC_OK);

        List<UserResponse> userResponses = JsonTransformer.mapResponseToList(getAllUsersResponse, UserResponse.class);
        specificWorkerId = getSpecificWorkerId(userResponses);
    }

    @Test(priority = 1)
    void promote_worker_to_manager_as_admin_test() {
        skyNestBackendClient.promoteUser(specificWorkerId).then().statusCode(SC_OK);
    }

    @Test(priority = 2)
    void promote_already_promoted_user_as_admin_test() {
        skyNestBackendClient.promoteUser(specificWorkerId).then().statusCode(SC_FORBIDDEN);
    }

    @Test(priority = 3)
    void demote_manager_to_worker_as_admin_test() {
        skyNestBackendClient.demoteUser(specificWorkerId).then().statusCode(SC_OK);
    }

    @Test(priority = 4)
    void demote_already_demoted_user_as_admin_test() {
        skyNestBackendClient.demoteUser(specificWorkerId).then().statusCode(SC_FORBIDDEN);
    }

}

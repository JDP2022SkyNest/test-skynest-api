package com.skynest;

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
    private static final String WORKER_EMAIL = "fedese7585@kahase.com";
    private UUID workerId;

    @BeforeClass
    void getWorkerId() throws IOException {
        loginAs(Roles.ADMIN);
        Response getAllUsersResponse = skyNestBackendClient.getAllUsers();
        getAllUsersResponse.then().statusCode(SC_OK);

        List<UserResponse> userResponses = JsonTransformer.mapResponseToList(getAllUsersResponse, UserResponse.class);
        workerId = getUserId(userResponses);
    }

    private UUID getUserId(List<UserResponse> userResponses) {
        for (int i = 0; i < userResponses.size(); i++) {
            UserResponse userResponse = userResponses.get(i);
            if (userResponse.getEmail().equals(WORKER_EMAIL)) {
                return userResponse.getId();
            }
        }
        return null;
    }

    @Test(priority = 1)
    void promote_worker_to_manager_as_admin_test() {
        skyNestBackendClient.promoteUser(workerId).then().statusCode(SC_OK);
    }

    @Test(priority = 2)
    void promote_already_promoted_user_as_admin_test() {
        skyNestBackendClient.promoteUser(workerId).then().statusCode(SC_FORBIDDEN);
    }

    @Test(priority = 3)
    void demote_manager_to_worker_as_admin_test() {
        skyNestBackendClient.demoteUser(workerId).then().statusCode(SC_OK);
    }

    @Test(priority = 4)
    void demote_already_demoted_user_as_admin_test() {
        skyNestBackendClient.demoteUser(workerId).then().statusCode(SC_FORBIDDEN);
    }

}

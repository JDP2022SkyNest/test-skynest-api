package com.skynest;

import com.skynest.models.UserResponse;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;

public class PromoteDemoteUserTest extends BaseTest {
    private UUID specificWorkerId;

    @BeforeClass
    void getWorkerId() {
        loginAs(Roles.ADMIN);
        Response getAllUsersResponse = skyNestBackendClient.getAllUsers();
        getAllUsersResponse.then().statusCode(SC_OK);
        UserResponse[] userResponses = getAllUsersResponse.body().as(UserResponse[].class);
        specificWorkerId = getSpecificWorkerId(List.of(userResponses));
    }

    @Test(priority = 1)
    void admin_should_be_able_to_promote_worker_to_manager() {
        skyNestBackendClient.promoteUser(specificWorkerId).then().statusCode(SC_OK);
    }

    @Test(priority = 2)
    void admin_should_not_be_able_to_promote_already_promoted_user() {
        skyNestBackendClient.promoteUser(specificWorkerId).then().statusCode(SC_FORBIDDEN);
    }

    @Test(priority = 3)
    void admin_should_be_able_to_demote_manager_to_worker() {
        skyNestBackendClient.demoteUser(specificWorkerId).then().statusCode(SC_OK);
    }

    @Test(priority = 4)
    void admin_should_not_be_able_to_demote_already_demoted_user() {
        skyNestBackendClient.demoteUser(specificWorkerId).then().statusCode(SC_FORBIDDEN);
    }

}

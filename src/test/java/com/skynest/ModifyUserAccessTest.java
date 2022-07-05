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
public class ModifyUserAccessTest extends LoggedUserBaseTest {

    public UUID userId;
    public static final String WORKER_EMAIL = "fedese7585@kahase.com";

    @BeforeClass
    void getWorkerId() throws IOException {
        Response getAllUsersResponse = skyNestBackendClient.getAllUsers();
        getAllUsersResponse.then().statusCode(SC_OK);
        List<UserResponse> userResponses = JsonTransformer.mapResponseToList(getAllUsersResponse, UserResponse.class);
        userId = getUserId(userResponses);
    }

    public UUID getUserId(List<UserResponse> userResponses) {
        for (int i = 0; i < userResponses.size(); i++) {
            UserResponse userResponse = userResponses.get(i);
            if (userResponse.getEmail().equals(WORKER_EMAIL)) {
                return userResponse.getId();
            }
        }
        return null;
    }


    @Test
    void disable_user_test() throws IOException {
        Response disableResponse = skyNestBackendClient.disableUser(userId);
        disableResponse.then().statusCode(SC_OK);
    }

    @AfterClass
    void enable_user_test() throws IOException {
        Response enableResponse = skyNestBackendClient.enableUser(userId);
        enableResponse.then().statusCode(SC_OK);
    }
}


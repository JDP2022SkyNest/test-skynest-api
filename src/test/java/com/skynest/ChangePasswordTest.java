package com.skynest;

import com.skynest.models.ChangePasswordRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;

public class ChangePasswordTest extends BaseTest {

    @Test
    void logged_worker_should_be_able_to_change_its_password() throws IOException {
        loginAs(Roles.WORKER);
        UUID loggedUserId = getLoggedUserId();

        ChangePasswordRequest changePasswordRequest = ChangePasswordRequest.generateValidChangePasswordRequest();
        Response changePasswordResponse = skyNestBackendClient.changePassword(changePasswordRequest, loggedUserId);
        changePasswordResponse.then().statusCode(SC_OK);
    }

    @Test
    void logged_admin_should_be_able_to_change_its_password() throws IOException {
        loginAs(Roles.ADMIN);
        UUID loggedUserId = getLoggedUserId();

        ChangePasswordRequest changePasswordRequest = ChangePasswordRequest.generateValidChangePasswordRequest();
        Response changePasswordResponse = skyNestBackendClient.changePassword(changePasswordRequest, loggedUserId);
        changePasswordResponse.then().statusCode(SC_OK);
    }
}

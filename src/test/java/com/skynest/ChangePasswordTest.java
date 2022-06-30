package com.skynest;

import com.skynest.models.ChangePasswordRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.apache.http.HttpStatus.SC_OK;

public class ChangePasswordTest extends LoggedUserBaseTest {

    @Test
    void logged_user_should_be_able_to_change_its_password() throws IOException {
        ChangePasswordRequest changePasswordRequest = ChangePasswordRequest.generateValidChangePasswordRequest();
        Response changePasswordResponse = skyNestBackendClient.changePassword(changePasswordRequest);
        changePasswordResponse.then().statusCode(SC_OK);
    }
}

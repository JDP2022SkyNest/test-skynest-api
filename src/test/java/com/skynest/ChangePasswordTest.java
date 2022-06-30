package com.skynest;

import com.skynest.models.ChangePasswordRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.apache.http.HttpStatus.SC_OK;

public class ChangePasswordTest extends LoggedUserBaseTest {

    @Test
    void logged_user_should_be_able_to_change_its_password(ChangePasswordRequest changePasswordRequest) throws IOException {
        Response response = skyNestBackendClient.changePassword(changePasswordRequest);
        response.then().statusCode(SC_OK);
    }
}

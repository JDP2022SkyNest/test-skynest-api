package com.skynest;

import com.skynest.models.ChangePasswordRequest;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;

public class ChangePasswordTest extends BaseTest {

    @Test(dataProvider = "loginAsWorkerOrAdmin")
    void logged_user_should_be_able_to_change_its_password(Roles role) {
        loginAs(role);
        UUID loggedUserId = getLoggedUserId();
        ChangePasswordRequest changePasswordRequest = ChangePasswordRequest.generateValidChangePasswordRequest();
        skyNestBackendClient.changePassword(changePasswordRequest, loggedUserId).then().statusCode(SC_OK);
    }

}

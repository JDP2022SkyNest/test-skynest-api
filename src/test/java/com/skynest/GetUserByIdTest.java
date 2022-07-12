package com.skynest;

import org.testng.annotations.Test;

import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;

public class GetUserByIdTest extends BaseTest {

    @Test(dataProvider = "loginAsWorkerOrAdmin")
    void logged_user_should_be_able_to_view_its_own_details(Roles role) {
        loginAs(role);
        UUID loggedUserId = getLoggedUserId();
        skyNestBackendClient.getUserById(loggedUserId).then().statusCode(SC_OK);
    }

}

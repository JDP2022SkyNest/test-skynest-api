package com.skynest;

import com.skynest.models.LoginRequest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;

import static org.apache.http.HttpStatus.SC_OK;

public class LoggedAdminBaseTest extends BaseTest {

    @BeforeClass
    public void loginAsAdmin() {
        LoginRequest body = new LoginRequest("christinezahar@yahoo.com", "Hris8833");
        Response loginResponse = skyNestBackendClient.login(body);
        loginResponse.then().statusCode(SC_OK);
    }
}

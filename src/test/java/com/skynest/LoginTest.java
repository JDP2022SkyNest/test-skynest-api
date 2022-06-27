package com.skynest;

import com.skynest.models.LoginRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.*;

public class LoginTest extends BaseTest {

    @Test
    void verified_user_should_be_successfully_logged_in() {
        LoginRequest body = new LoginRequest("yagaj78380@jrvps.com", "System123");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_OK);
    }

    @Test
    void not_verified_user_should_not_be_logged_in() {
        LoginRequest body = new LoginRequest("miwix46540@jrvps.com", "System123");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_FORBIDDEN);
    }

    @Test
    void user_should_not_be_logged_in_entering_valid_email_and_invalid_password() {
        LoginRequest body = new LoginRequest("yagaj78380@jrvps.com", "invalidpass");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_UNAUTHORIZED);
    }

    @Test
    void user_should_not_be_logged_in_entering_valid_email_and_blank_password() {
        LoginRequest body = new LoginRequest("yagaj78380@jrvps.com", "");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_UNAUTHORIZED);
    }

    @Test
    void user_should_not_be_logged_in_entering_invalid_email_and_valid_password() {
        LoginRequest body = new LoginRequest("invalidemail@yahoo.com", "System123");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_NOT_FOUND);
    }

    @Test
    void user_should_not_be_logged_in_entering_invalid_email_and_invalid_password() {
        LoginRequest body = new LoginRequest("invalidemail@yahoo.com", "invalidpass");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_NOT_FOUND);
    }

    @Test
    void user_should_not_be_logged_in_entering_invalid_email_and_blank_password() {
        LoginRequest body = new LoginRequest("invalidemail@yahoo.com", "");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_NOT_FOUND);
    }

    @Test
    void user_should_not_be_logged_in_entering_blank_email_and_valid_password() {
        LoginRequest body = new LoginRequest("", "System123");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_NOT_FOUND);
    }

    @Test
    void user_should_not_be_logged_in_entering_blank_email_and_invalid_password() {
        LoginRequest body = new LoginRequest("", "invalidpass");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_NOT_FOUND);
    }

    @Test
    void user_should_not_be_logged_in_entering_blank_email_and_blank_password() {
        LoginRequest body = new LoginRequest("", "");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_NOT_FOUND);
    }

    @Test
    void user_should_be_locked_after_five_invalid_attempts() {
        LoginRequest body = new LoginRequest("vobipi9211@kahase.com", "invalid");
        Response response = null;
        for (int i = 0; i <= 5; i++) {
            response = skyNestBackendClient.login(body);
        }
        response.then().statusCode(429);
    }
}

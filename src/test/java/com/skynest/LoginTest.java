package com.skynest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skynest.models.LoginRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.net.URISyntaxException;

import static org.apache.http.HttpStatus.*;

public class LoginTest extends BaseTest {

    public LoginTest() throws URISyntaxException {
    }

    @Test
    void verified_user_should_be_successfully_logged_in() throws JsonProcessingException {
        LoginRequest body = new LoginRequest("christinezahar@yahoo.com", "Hris8833");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_OK);
    }

    @Test
    void not_verified_user_should_not_be_logged_in() throws JsonProcessingException {
        LoginRequest body = new LoginRequest("lipafaj444@hekarro.com", "tempEmail8833");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_FORBIDDEN);
    }

    @Test
    void user_should_not_be_logged_in_entering_valid_email_and_invalid_password() throws JsonProcessingException {
        LoginRequest body = new LoginRequest("christinezahar@yahoo.com", "invalidpass");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_UNAUTHORIZED);
    }

    @Test
    void user_should_not_be_logged_in_entering_valid_email_and_blank_password() throws JsonProcessingException {
        LoginRequest body = new LoginRequest("christinezahar@yahoo.com", "");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_UNAUTHORIZED);
    }

    @Test
    void user_should_not_be_logged_in_entering_invalid_email_and_valid_password() throws JsonProcessingException {
        LoginRequest body = new LoginRequest("invalidemail@yahoo.com", "Hris8833");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_NOT_FOUND);
    }

    @Test
    void user_should_not_be_logged_in_entering_invalid_email_and_invalid_password() throws JsonProcessingException {
        LoginRequest body = new LoginRequest("invalidemail@yahoo.com", "Hris8833");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_NOT_FOUND);
    }

    @Test
    void user_should_not_be_logged_in_entering_invalid_email_and_blank_password() throws JsonProcessingException {
        LoginRequest body = new LoginRequest("invalidemail@yahoo.com", "");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_NOT_FOUND);
    }

    @Test
    void user_should_not_be_logged_in_entering_blank_email_and_valid_password() throws JsonProcessingException {
        LoginRequest body = new LoginRequest("invalidemail@yahoo.com", "");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_NOT_FOUND);
    }

    @Test
    void user_should_not_be_logged_in_entering_blank_email_and_invalid_password() throws JsonProcessingException {
        LoginRequest body = new LoginRequest("invalidemail@yahoo.com", "");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_NOT_FOUND);
    }

    @Test
    void user_should_not_be_logged_in_entering_blank_email_and_blank_password() throws JsonProcessingException {
        LoginRequest body = new LoginRequest("invalidemail@yahoo.com", "");
        Response response = skyNestBackendClient.login(body);
        response.then().statusCode(SC_NOT_FOUND);
    }

    //TODO Tests for login attempts
}

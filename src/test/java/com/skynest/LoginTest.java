package com.skynest;

import com.skynest.models.LoginRequest;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.*;

public class LoginTest extends BaseTest {

    @DataProvider(name = "invalidCredentialCombinations")
    public static Object[][] invalidCredentials() {
        return new Object[][]{
                {"invalidemail@yahoo.com", "System123"},
                {"invalidemail@yahoo.com", "invalidpass"},
                {"invalidemail@yahoo.com", ""},
                {"", "System123"},
                {"", "invalidpass"},
                {"", ""}
        };
    }

    @Test
    void verified_user_should_be_successfully_logged_in() {
        LoginRequest body = new LoginRequest("yagaj78380@jrvps.com", "System123");
        Response loginResponse = skyNestBackendClient.login(body);
        loginResponse.then().statusCode(SC_OK);
    }

    @Test
    void not_verified_user_should_not_be_logged_in() {
        LoginRequest body = new LoginRequest("miwix46540@jrvps.com", "System123");
        Response loginResponse = skyNestBackendClient.login(body);
        loginResponse.then().statusCode(SC_FORBIDDEN);
    }

    @Test
    void user_should_not_be_logged_in_entering_valid_email_and_invalid_password() {
        LoginRequest body = new LoginRequest("yagaj78380@jrvps.com", "invalidpass");
        Response loginResponse = skyNestBackendClient.login(body);
        loginResponse.then().statusCode(SC_UNAUTHORIZED);
    }

    @Test
    void user_should_not_be_logged_in_entering_valid_email_and_blank_password() {
        LoginRequest body = new LoginRequest("yagaj78380@jrvps.com", "");
        Response loginResponse = skyNestBackendClient.login(body);
        loginResponse.then().statusCode(SC_UNAUTHORIZED);
    }

    @Test(dataProvider = "invalidCredentialCombinations")
    void user_should_not_log_in_with_invalid_credentials(String email, String password) {
        LoginRequest body = new LoginRequest(email, password);
        Response loginResponse = skyNestBackendClient.login(body);
        loginResponse.then().statusCode(SC_NOT_FOUND);
    }

    @Test
    void user_should_be_locked_after_five_invalid_attempts() {
        LoginRequest body = new LoginRequest("vobipi9211@kahase.com", "invalid");
        Response loginResponse = null;
        for (int i = 0; i <= 5; i++) {
            loginResponse = skyNestBackendClient.login(body);
        }
        loginResponse.then().statusCode(429);
    }
}

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

    @DataProvider(name = "invalidPassword")
    public static Object[][] invalidPassword() {
        return new Object[][]{
                {"yagaj78380@jrvps.com", "invalidpass"},
                {"yagaj78380@jrvps.com", ""}
        };
    }

    @DataProvider(name = "maxNumberOfLoginAttempts")
    public static Object[][] maxAttempts() {
        return new Object[][]{
                {5}
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

    @Test(dataProvider = "invalidPassword")
    void user_should_not_log_in_with_invalid_or_blank_password(String email, String password) {
        LoginRequest body = new LoginRequest(email, password);
        Response loginResponse = skyNestBackendClient.login(body);
        loginResponse.then().statusCode(SC_UNAUTHORIZED);
    }

    @Test(dataProvider = "invalidCredentialCombinations")
    void user_should_not_log_in_with_invalid_credentials(String email, String password) {
        LoginRequest body = new LoginRequest(email, password);
        Response loginResponse = skyNestBackendClient.login(body);
        loginResponse.then().statusCode(SC_NOT_FOUND);
    }

    @Test(dataProvider = "maxNumberOfLoginAttempts")
    void user_should_be_locked_after_number_of_invalid_attempts(int maxAttempts) {
        LoginRequest body = new LoginRequest("vobipi9211@kahase.com", "invalid");
        Response loginResponse = null;
        for (int i = 0; i <= maxAttempts; i++) {
            loginResponse = skyNestBackendClient.login(body);
        }
        loginResponse.then().statusCode(429);
    }
}
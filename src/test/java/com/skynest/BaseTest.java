package com.skynest;

import com.skynest.clients.SkyNestBackendClient;
import com.skynest.config.properties.PropertiesReader;
import com.skynest.constants.Credentials;
import com.skynest.models.LoggedUserResponse;
import com.skynest.models.LoginRequest;
import com.skynest.models.UserResponse;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;

public class BaseTest {
    private static final String TARGET_DOMAIN = "targetDomain";
    protected SkyNestBackendClient skyNestBackendClient;

    @BeforeSuite(alwaysRun = true)
    public void setupEnvironment() throws URISyntaxException {
        PropertiesReader propertiesReader = PropertiesReader.getInstance();
        String targetDomain = System.getProperty(TARGET_DOMAIN, propertiesReader.getProperty(TARGET_DOMAIN));
        skyNestBackendClient = new SkyNestBackendClient(targetDomain);
    }

    public void loginAs(Roles role) {
        switch (role) {
            case ADMIN:
                loginUser("christinezahar@yahoo.com", "Hris8833");
                break;
            case WORKER:
                loginUser("hristina.zaharieva@htecgroup.com", "Hris8833");
                break;
        }
    }

    public void loginUser(String email, String password) {
        LoginRequest body = new LoginRequest(email, password);
        Response loginResponse = skyNestBackendClient.login(body);
        loginResponse.then().statusCode(SC_OK);
    }

    public UUID getLoggedUserId() {
        Response response = skyNestBackendClient.getLoggedUser();
        LoggedUserResponse loggedUserResponse = response.as(LoggedUserResponse.class);
        return loggedUserResponse.getUuid();
    }

    public UUID getSpecificWorkerId(List<UserResponse> userResponses) {
        for (int i = 0; i < userResponses.size(); i++) {
            UserResponse userResponse = userResponses.get(i);
            if (userResponse.getEmail().equals(Credentials.WORKER_EMAIL)) {
                return userResponse.getId();
            }
        }
        return null;
    }

    @DataProvider(name = "loginAsWorkerOrAdmin")
    public static Object[][] loginAsWorkerOrAdmin() {
        return new Object[][]{{Roles.WORKER}, {Roles.ADMIN}};
    }

}


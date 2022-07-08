package com.skynest;

import com.skynest.clients.SkyNestBackendClient;
import com.skynest.config.properties.PropertiesReader;
import com.skynest.models.LoggedUserResponse;
import com.skynest.models.LoginRequest;
import com.skynest.utils.JsonTransformer;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.URISyntaxException;
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

    public void loginUser(String email, String password) {
        LoginRequest body = new LoginRequest(email, password);
        Response loginResponse = skyNestBackendClient.login(body);
        loginResponse.then().statusCode(SC_OK);
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

    public UUID getLoggedUserId() throws IOException {
        Response response = skyNestBackendClient.getLoggedUser();
        LoggedUserResponse loggedUserResponse = JsonTransformer.mapResponse(response, LoggedUserResponse.class);
        UUID uuid = loggedUserResponse.getUuid();
        return uuid;
    }

}


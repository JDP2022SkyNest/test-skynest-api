package com.skynest;

import com.skynest.clients.SkyNestBackendClient;
import com.skynest.config.properties.PropertiesReader;
import com.skynest.models.LoginRequest;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;

import java.net.URISyntaxException;

import static org.apache.http.HttpStatus.SC_OK;

public class BaseTest {
    protected SkyNestBackendClient skyNestBackendClient;
    private static final String TARGET_DOMAIN = "targetDomain";

    @BeforeSuite(alwaysRun = true)
    public void setupEnvironment() throws URISyntaxException {
        PropertiesReader propertiesReader = PropertiesReader.getInstance();
        String targetDomain = System.getProperty(TARGET_DOMAIN, propertiesReader.getProperty(TARGET_DOMAIN));
        skyNestBackendClient = new SkyNestBackendClient(targetDomain);
    }

    public void loginUser(String email, String password){
        LoginRequest body = new LoginRequest(email, password);
        Response loginResponse = skyNestBackendClient.login(body);
        loginResponse.then().statusCode(SC_OK);
    }

    public void loginAs(Roles role) {
        switch(role) {
            case ADMIN: loginUser("christinezahar@yahoo.com","Hris8833");
            break;
            case WORKER: loginUser("hristina.zaharieva@htecgroup.com","Hris8833");
            break;
        }
    }

}

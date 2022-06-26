package com.skynest;

import com.skynest.clients.SkyNestBackendClient;
import com.skynest.models.ApiConstants;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import java.net.URISyntaxException;

public class BaseTest {
    //Protected access modifier because it's used in all child classes
    protected SkyNestBackendClient skyNestBackendClient = new SkyNestBackendClient();

    public BaseTest() throws URISyntaxException {
    }

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ApiConstants.BASE_URL;
    }
}

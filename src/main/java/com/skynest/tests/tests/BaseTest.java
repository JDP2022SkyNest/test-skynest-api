package com.skynest.tests.tests;

import com.skynest.tests.files.EndpointConstants;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = EndpointConstants.URL;
    }

}

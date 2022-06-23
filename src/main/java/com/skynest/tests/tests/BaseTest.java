package com.skynest.tests.tests;

import com.skynest.tests.api.Endpoints;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = Endpoints.BASE_URL;
    }
}

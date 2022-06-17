package com.skynest.tests.tests;

import com.skynest.tests.files.ApiConstants;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = ApiConstants.BASE_URL;
    }
}

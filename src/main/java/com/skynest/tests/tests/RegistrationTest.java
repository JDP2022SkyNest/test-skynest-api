package com.skynest.tests.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skynest.tests.files.ApiConstants;
import com.skynest.tests.files.RegistrationRequest;
import com.skynest.tests.files.RegistrationResponse;
import com.skynest.tests.utils.BaseTransformer;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RegistrationTest extends BaseTest {
    @Test(dataProvider = "UserData")
    void registering_new_valid_user_should_return_specified_response(
            RegistrationRequest registrationPayload) throws JsonProcessingException {

        Response response =
                given()
                        .log().all().header("Content-Type", "application/json")
                        .body(BaseTransformer.objectToJson(registrationPayload))
                        .when()
                        .post(ApiConstants.REGISTER_ENDPOINT);

        RegistrationResponse registrationResponse = response.as(RegistrationResponse.class);

        Assert.assertNotNull(registrationResponse.getId());

        Assert.assertEquals(registrationResponse.getName(), registrationPayload.getName());
        Assert.assertEquals(registrationResponse.getSurname(), registrationPayload.getSurname());
        Assert.assertEquals(registrationResponse.getEmail(), registrationPayload.getEmail());
        Assert.assertEquals(registrationResponse.getPhoneNumber(), registrationPayload.getPhoneNumber());
        Assert.assertEquals(registrationResponse.getAddress(), registrationPayload.getAddress());
    }


    @DataProvider(name = "UserData")
    public Object[][] getUserData() {
        return new Object[][]{
                new Object[]{RegistrationRequest.generateValidRegistrationRequest()}
        };
    }
}
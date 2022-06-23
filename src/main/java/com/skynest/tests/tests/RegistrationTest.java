package com.skynest.tests.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.skynest.tests.api.Endpoints;
import com.skynest.tests.api.RegistrationRequest;
import com.skynest.tests.api.RegistrationResponse;
import com.skynest.tests.utils.JsonMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

public class RegistrationTest extends BaseTest {
    private final SoftAssert softAssert = new SoftAssert();

    @Test(dataProvider = "UserData")
    void registering_new_valid_user_should_return_specified_response(RegistrationRequest registrationPayload) throws JsonProcessingException {
        final Response response = given()
                .header(CONTENT_TYPE, APPLICATION_JSON.getMimeType())
                .log().all()
                .body(JsonMapper.objectToJson(registrationPayload))
                .response()
                .log().all()
                .when()
                .post(Endpoints.REGISTER_ENDPOINT);

        RegistrationResponse registrationResponse = response.as(RegistrationResponse.class);

        Assert.assertNotNull(registrationResponse.getId());
        Assert.assertEquals(registrationResponse.getName(), registrationPayload.getName());
        Assert.assertEquals(registrationResponse.getSurname(), registrationPayload.getSurname());
        Assert.assertEquals(registrationResponse.getEmail(), registrationPayload.getEmail());
        Assert.assertEquals(registrationResponse.getPhoneNumber(), registrationPayload.getPhoneNumber());
        Assert.assertEquals(registrationResponse.getAddress(), registrationPayload.getAddress());
        softAssert.assertAll();
    }

    @DataProvider(name = "UserData")
    public Object[][] getUserData() {
        return new Object[][]{
                new Object[]{RegistrationRequest.generateValidRegistrationRequest()}
        };
    }
}

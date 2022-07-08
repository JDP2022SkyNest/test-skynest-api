package com.skynest;

import com.skynest.models.RegistrationRequest;
import com.skynest.models.RegistrationResponse;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class RegistrationTest extends BaseTest {

    @Test(dataProvider = "UserData")
    void registering_new_valid_user_should_return_specified_response(RegistrationRequest registrationRequest) {
        Response registerUserResponse = skyNestBackendClient.register(registrationRequest);
        registerUserResponse.then().statusCode(SC_OK);

        RegistrationResponse registrationResponse = registerUserResponse.as(RegistrationResponse.class);

        assertNotNull(registrationResponse.getId());

        assertEquals(registrationResponse.getName(), registrationRequest.getName());
        assertEquals(registrationResponse.getSurname(), registrationRequest.getSurname());
        assertEquals(registrationResponse.getEmail(), registrationRequest.getEmail());
        assertEquals(registrationResponse.getPhoneNumber(), registrationRequest.getPhoneNumber());
        assertEquals(registrationResponse.getAddress(), registrationRequest.getAddress());
    }

    @DataProvider(name = "UserData")
    public Object[][] getUserData() {
        return new Object[][]{new Object[]{RegistrationRequest.generateValidRegistrationRequest()}};
    }
}
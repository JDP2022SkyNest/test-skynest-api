package com.skynest;

import com.skynest.models.RegistrationRequest;
import com.skynest.models.RegistrationResponse;
import com.skynest.utils.JsonTransformer;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.apache.http.HttpStatus.SC_OK;

public class RegistrationTest extends BaseTest {

    @Test(dataProvider = "UserData")
    void registering_new_valid_user_should_return_specified_response(RegistrationRequest registrationRequest) throws IOException {

        Response registerUserResponse = skyNestBackendClient.register(registrationRequest);
        registerUserResponse.then().statusCode(SC_OK);

        RegistrationResponse registrationResponse = JsonTransformer.mapResponse(registerUserResponse, RegistrationResponse.class);

        Assert.assertNotNull(registrationResponse.getId());

        Assert.assertEquals(registrationResponse.getName(), registrationRequest.getName());
        Assert.assertEquals(registrationResponse.getSurname(), registrationRequest.getSurname());
        Assert.assertEquals(registrationResponse.getEmail(), registrationRequest.getEmail());
        Assert.assertEquals(registrationResponse.getPhoneNumber(), registrationRequest.getPhoneNumber());
        Assert.assertEquals(registrationResponse.getAddress(), registrationRequest.getAddress());
    }

    @DataProvider(name = "UserData")
    public Object[][] getUserData() {
        return new Object[][]{new Object[]{RegistrationRequest.generateValidRegistrationRequest()}};
    }
}
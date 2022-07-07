package com.skynest;

import com.skynest.models.LoggedUserResponse;
import com.skynest.utils.JsonTransformer;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;

public class GetUserByIdTest extends BaseTest {
    @Test
    void get_worker_details_as_worker() throws IOException {
        loginAs(Roles.WORKER);
        Response response = skyNestBackendClient.getLoggedUser();
        LoggedUserResponse loggedUserResponse = JsonTransformer.mapResponse(response,LoggedUserResponse.class);
        UUID uuid = loggedUserResponse.getUuid();

        Response getUserResponse = skyNestBackendClient.getUserById(uuid);
        getUserResponse.then().statusCode(SC_OK);
    }


    @Test
    void get_admin_details_as_admin() throws IOException {
        loginAs(Roles.ADMIN);
        Response response = skyNestBackendClient.getLoggedUser();
        LoggedUserResponse loggedUserResponse = JsonTransformer.mapResponse(response, LoggedUserResponse.class);
        UUID uuid = loggedUserResponse.getUuid();

        Response getUserResponse = skyNestBackendClient.getUserById(uuid);
        getUserResponse.then().statusCode(SC_OK);
    }

}

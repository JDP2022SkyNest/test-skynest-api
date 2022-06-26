package com.skynest.clients;

import com.skynest.models.ApiConstants;
import com.skynest.models.LoginRequest;
import io.restassured.response.Response;

import java.net.URISyntaxException;

public class SkyNestBackendClient extends BaseClient {
    public SkyNestBackendClient() throws URISyntaxException {
        //super("http://localhost:8080");
        super(ApiConstants.BASE_URL);
    }

    public Response login(LoginRequest loginRequest) {
        Response response = requestMaker()
                .body(loginRequest)
                .post("/public/login");
        setAuthToken(response.getHeader("Authorization"));
        return response;
    }

    public Response getAllUsers() {
        Response response = requestMaker()
                .get("/users");
        return response;
    }

    public Response logout() {
        Response response = requestMaker()
                .post("/auth/logout");
        return response;
    }

}

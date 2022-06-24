package com.skynest.clients;

import com.skynest.files.LoginRequest;
import com.skynest.files.LogoutRequest;
import io.restassured.response.Response;

import java.net.URISyntaxException;

public class SkyNestBackendClient extends BaseClient {
    public SkyNestBackendClient() throws URISyntaxException {
        super("http://localhost:8080");
    }
    public Response login(LoginRequest loginRequest) {
        Response response = requestMaker()
                .body(loginRequest)
                .post("/public/login");
        BaseClient.setAuthToken(response.getHeader("Authorization"));
        return response;
    }

    public Response getAllUsers() {
        Response response = requestMaker()
                .get("/users");
        return response;
    }

    public Response logout(LogoutRequest logoutRequest) {
        Response response = requestMaker()
                .header(logoutRequest)
                .post("/auth/logout");
        return response;

    }

}

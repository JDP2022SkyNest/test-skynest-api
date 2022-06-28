package com.skynest.clients;

import com.skynest.models.ApiConstants;
import com.skynest.models.LoginRequest;
import io.restassured.response.Response;

import java.net.URISyntaxException;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

public class SkyNestBackendClient extends BaseClient {
    public SkyNestBackendClient() throws URISyntaxException {
        super(ApiConstants.BASE_URL);
    }

    public Response login(LoginRequest loginRequest) {
        Response response = requestMaker()
                .body(loginRequest)
                .post("/public/login");
        setAuthToken(response.getHeader(AUTHORIZATION));
        return response;
    }

    public Response getAllUsers() {
        return requestMaker()
                .get("/users");
    }

    public Response logout() {
        return requestMaker()
                .post("/auth/logout");
    }
}

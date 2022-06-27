package com.skynest.tests.clients;

import com.skynest.tests.files.LoginRequest;
import io.restassured.response.Response;

import java.net.URISyntaxException;

public class SkyNestBackendClient extends BaseClient {
    public SkyNestBackendClient() throws URISyntaxException {
        super("http://13.94.241.83:8080");
    }
    public Response login(LoginRequest loginRequest) {
        Response response = requestMaker()
                .body(loginRequest)
                .post("/public/login");
        BaseClient.setAuthToken(response.getHeader("Authorization"));
        return response;
    }

//    public Response get() {
//        Response response = requestMaker()
//                .get("/users");
//        return response;
//    }

}

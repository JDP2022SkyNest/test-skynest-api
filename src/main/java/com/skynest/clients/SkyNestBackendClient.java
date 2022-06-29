package com.skynest.clients;

import com.skynest.models.*;
import com.skynest.utils.JsonTransformer;
import io.restassured.response.Response;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

public class SkyNestBackendClient extends BaseClient {
    public SkyNestBackendClient(String targetDomain) throws URISyntaxException {
        super(targetDomain);
    }

    public Response register(RegistrationRequest registrationRequest) {
        return requestMaker()
                .body(registrationRequest)
                .post(ApiConstants.REGISTER_ENDPOINT);
    }

    public Response login(LoginRequest loginRequest) {
        Response response = requestMaker()
                .body(loginRequest)
                .post(ApiConstants.LOGIN_ENDPOINT);
        setAuthToken(response.getHeader(AUTHORIZATION));
        return response;
    }

    public Response getLoggedUser() {
        return requestMaker()
                .get(ApiConstants.ME_ENDPOINT);
    }

    public Response getAllUsers() {
        return requestMaker()
                .get(ApiConstants.GET_ALL_USERS_ENDPOINT);
    }

    public Response getUserById() throws IOException {
        Response response = getLoggedUser();
        LoggedUserResponse loggedUserResponse = JsonTransformer.mapResponseToLoggedUserResponse(response);
        return requestMaker()
                .get(ApiConstants.GET_USER_BY_ID_ENDPOINT+loggedUserResponse.getUuid());
    }

    public Response editUserById(EditRequest editRequest) throws IOException {
        Response response = getLoggedUser();
        LoggedUserResponse loggedUserResponse = JsonTransformer.mapResponseToLoggedUserResponse(response);
        return requestMaker()
                .body(editRequest)
                .put(ApiConstants.EDIT_USER_BY_ID_ENDPOINT+loggedUserResponse.getUuid());
    }


    public Response logout() {
        return requestMaker()
                .post(ApiConstants.LOGOUT_ENDPOINT);
    }
}

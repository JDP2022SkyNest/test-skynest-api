package com.skynest.clients;

import com.skynest.models.*;
import com.skynest.utils.JsonTransformer;
import io.restassured.response.Response;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

public class SkyNestBackendClient extends BaseClient {
    public SkyNestBackendClient(String targetDomain) throws URISyntaxException {
        super(targetDomain);
    }

    public Response register(RegistrationRequest registrationRequest) {
        return requestMaker()
                .body(registrationRequest)
                .post(ApiConstants.REGISTER_PATH);
    }

    public Response login(LoginRequest loginRequest) {
        Response response = requestMaker()
                .body(loginRequest)
                .post(ApiConstants.LOGIN_PATH);
        setAuthToken(response.getHeader(AUTHORIZATION));
        return response;
    }

    public Response getLoggedUser() {
        return requestMaker()
                .get(ApiConstants.ME_PATH);
    }

    public Response getAllUsers() {
        return requestMaker()
                .get(ApiConstants.GET_ALL_USERS_PATH);
    }

    public Response getUserById() throws IOException {
        Response response = getLoggedUser();
        LoggedUserResponse loggedUserResponse = JsonTransformer.mapResponse(response, LoggedUserResponse.class);
        return requestMaker()
                .get(ApiConstants.USERS_PATH + loggedUserResponse.getUuid());
    }

    public Response editUserById(EditRequest editRequest) throws IOException {
        Response response = getLoggedUser();
        LoggedUserResponse loggedUserResponse = JsonTransformer.mapResponse(response, LoggedUserResponse.class);
        return requestMaker()
                .body(editRequest)
                .put(ApiConstants.USERS_PATH + loggedUserResponse.getUuid());
    }

    public Response changePassword(ChangePasswordRequest changePasswordRequest) throws IOException {
        Response response = getLoggedUser();
        LoggedUserResponse loggedUserResponse = JsonTransformer.mapResponse(response, LoggedUserResponse.class);
        return requestMaker()
                .body(changePasswordRequest)
                .put(ApiConstants.CHANGE_PASSWORD_PATH + loggedUserResponse.getUuid());
    }

    public Response logout() {
        return requestMaker()
                .post(ApiConstants.LOGOUT_PATH);
    }

    public Response disableUser(UUID userId) throws IOException {
        return requestMaker()
                .put(ApiConstants.USERS_PATH + userId + ApiConstants.DISABLE_PATH);
    }

    public Response enableUser(UUID userId) throws IOException {
        return requestMaker()
                .put(ApiConstants.USERS_PATH + userId + ApiConstants.ENABLE_PATH);
    }
}

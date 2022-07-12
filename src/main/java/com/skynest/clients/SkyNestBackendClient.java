package com.skynest.clients;

import com.skynest.constants.ApiConstants;
import com.skynest.models.ChangePasswordRequest;
import com.skynest.models.EditRequest;
import com.skynest.models.LoginRequest;
import com.skynest.models.RegistrationRequest;
import io.restassured.response.Response;

import java.net.URISyntaxException;
import java.util.UUID;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

public class SkyNestBackendClient extends BaseClient {
    private static String userId = "userId";
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

    public Response getUserById(UUID uuid) {
        return requestMaker()
                .pathParam(userId, uuid)
                .get(ApiConstants.USER_BY_ID_PATH);
    }

    public Response editUserById(EditRequest editRequest, UUID uuid) {
        return requestMaker()
                .body(editRequest)
                .pathParam(userId, uuid)
                .put(ApiConstants.USER_BY_ID_PATH);
    }

    public Response changePassword(ChangePasswordRequest changePasswordRequest, UUID uuid) {
        return requestMaker()
                .body(changePasswordRequest)
                .pathParam(userId, uuid)
                .put(ApiConstants.CHANGE_PASSWORD_PATH);
    }

    public Response logout() {
        return requestMaker()
                .post(ApiConstants.LOGOUT_PATH);
    }

    public Response disableUser(UUID uuid) {
        return requestMaker()
                .pathParam(userId, uuid)
                .put(ApiConstants.DISABLE_PATH);
    }

    public Response enableUser(UUID uuid) {
        return requestMaker()
                .pathParam(userId, uuid)
                .put(ApiConstants.ENABLE_PATH);
    }

    public Response promoteUser(UUID uuid) {
        return requestMaker()
                .pathParam(userId, uuid)
                .put(ApiConstants.PROMOTE_PATH);
    }

    public Response demoteUser(UUID uuid) {
        return requestMaker()
                .pathParam(userId, uuid)
                .put(ApiConstants.DEMOTE_PATH);
    }
}

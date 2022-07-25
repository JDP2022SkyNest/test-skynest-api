package com.skynest.clients;

import com.skynest.constants.ApiConstants;
import com.skynest.models.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.net.URISyntaxException;
import java.util.UUID;

import static org.apache.http.HttpHeaders.AUTHORIZATION;

public class SkyNestBackendClient extends BaseClient {
    private static String userIdKey = "userId";
    private static String bucketIdKey = "bucketId";

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

    public Response getUserById(UUID userId) {
        return requestMaker()
                .pathParam(userIdKey, userId)
                .get(ApiConstants.USER_BY_ID_PATH);
    }

    public Response editUserById(EditUserRequest editRequest, UUID userId) {
        return requestMaker()
                .body(editRequest)
                .pathParam(userIdKey, userId)
                .put(ApiConstants.USER_BY_ID_PATH);
    }

    public Response changePassword(ChangePasswordRequest changePasswordRequest, UUID userId) {
        return requestMaker()
                .body(changePasswordRequest)
                .pathParam(userIdKey, userId)
                .put(ApiConstants.CHANGE_PASSWORD_PATH);
    }

    public Response logout() {
        return requestMaker()
                .post(ApiConstants.LOGOUT_PATH);
    }

    public Response disableUser(UUID userId) {
        return requestMaker()
                .pathParam(userIdKey, userId)
                .put(ApiConstants.DISABLE_PATH);
    }

    public Response enableUser(UUID userId) {
        return requestMaker()
                .pathParam(userIdKey, userId)
                .put(ApiConstants.ENABLE_PATH);
    }

    public Response promoteUser(UUID userId) {
        return requestMaker()
                .pathParam(userIdKey, userId)
                .put(ApiConstants.PROMOTE_PATH);
    }

    public Response demoteUser(UUID userId) {
        return requestMaker()
                .pathParam(userIdKey, userId)
                .put(ApiConstants.DEMOTE_PATH);
    }

    public Response createBucket(CreateBucketRequest createBucketRequest) {
        return requestMaker()
                .body(createBucketRequest)
                .post(ApiConstants.BUCKETS_PATH);
    }

    public Response getAllBuckets() {
        return requestMaker()
                .get(ApiConstants.BUCKETS_PATH);
    }

    public Response getBucketById(UUID bucketId) {
        return requestMaker()
                .pathParam(bucketIdKey, bucketId)
                .get(ApiConstants.BUCKET_DETAILS_PATH);
    }

    public Response getBucketContent(UUID bucketId) {
        return requestMaker()
                .pathParam(bucketIdKey, bucketId)
                .get(ApiConstants.BUCKET_BY_ID_PATH);
    }

    public Response deleteBucket(UUID bucketId) {
        return requestMaker()
                .pathParam(bucketIdKey, bucketId)
                .put(ApiConstants.DELETE_BUCKET_PATH);
    }

    public Response editBucket(EditBucketRequest editBucketRequest, UUID bucketId) {
        return requestMaker()
                .body(editBucketRequest)
                .pathParam(bucketIdKey, bucketId)
                .put(ApiConstants.BUCKET_BY_ID_PATH);
    }

    public Response restoreBucket(UUID bucketId) {
        return requestMaker()
                .pathParam(bucketIdKey, bucketId)
                .put(ApiConstants.RESTORE_BUCKET_PATH);
    }

    public Response createFolder(CreateFolderRequest createFolderRequest) {
        return requestMaker()
                .body(createFolderRequest)
                .post(ApiConstants.FOLDERS_PATH);
    }

    public Response uploadFile(UUID bucketId) {
        return requestMaker()
                .contentType(ContentType.MULTIPART)
                .multiPart("file", new File("src/test/resources/FileForUpload.txt"))
                .pathParam(bucketIdKey, bucketId)
                .post(ApiConstants.UPLOAD_FILE_TO_BUCKET);
    }

}

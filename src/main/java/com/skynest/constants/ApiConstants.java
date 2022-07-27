package com.skynest.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiConstants {
    public static final String REGISTER_PATH = "/public/register";
    public static final String LOGIN_PATH = "/public/login";
    public static final String ME_PATH = "/users/me";
    public static final String USER_BY_ID_PATH = "/users/{userId}";
    public static final String GET_ALL_USERS_PATH = "/users";
    public static final String LOGOUT_PATH = "/auth/logout";
    public static final String CHANGE_PASSWORD_PATH = "/users/{userId}/password-change";
    public static final String ENABLE_PATH = "users/{userId}/enable";
    public static final String DISABLE_PATH = "users/{userId}/disable";
    public static final String PROMOTE_PATH = "users/{userId}/promote";
    public static final String DEMOTE_PATH = "users/{userId}/demote";
    public static final String BUCKETS_PATH = "/buckets";
    public static final String BUCKET_DETAILS_PATH = "/buckets/{bucketId}/info";
    public static final String BUCKET_BY_ID_PATH = "/buckets/{bucketId}";
    public static final String DELETE_BUCKET_PATH = "/buckets/{bucketId}/delete";
    public static final String DELETED_BUCKETS_PATH = "/buckets/deleted";
    public static final String RESTORE_BUCKET_PATH = "/buckets/{bucketId}/restore";
    public static final String FOLDERS_PATH = "/folders";
    public static final String UPLOAD_FILE_TO_BUCKET = "/files/bucket/{bucketId}";

}


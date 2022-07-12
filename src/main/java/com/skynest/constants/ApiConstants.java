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
}

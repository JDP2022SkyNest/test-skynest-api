package com.skynest.models;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiConstants {
    public static final String REGISTER_PATH = "/public/register";
    public static final String LOGIN_PATH = "/public/login";
    public static final String ME_PATH = "/users/me";
    public static final String GET_USER_BY_ID_PATH = "/users/{uuid}";
    public static final String GET_ALL_USERS_PATH = "/users";
    public static final String USERS_PATH = "/users/";
    public static final String LOGOUT_PATH = "/auth/logout";
    public static final String EDIT_USER_BY_ID_PATH = "/users/{uuid}";
    public static final String CHANGE_PASSWORD_PATH = "/users/password-change/{uuid}";
    public static final String ENABLE_PATH = "/enable";
    public static final String DISABLE_PATH = "/disable";
}

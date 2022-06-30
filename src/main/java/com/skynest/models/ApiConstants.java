package com.skynest.models;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiConstants {
    public static final String REGISTER_ENDPOINT = "/public/register";
    public static final String LOGIN_ENDPOINT = "/public/login";
    public static final String ME_ENDPOINT = "/users/me";
    public static final String USERS_PATH = "/users";
    public static final String LOGOUT_ENDPOINT = "/auth/logout";

    public static final String CHANGE_PASSWORD_ENDPOINT = "/users/password-change/";
}

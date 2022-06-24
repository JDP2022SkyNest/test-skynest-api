package com.skynest.files;

import com.skynest.clients.BaseClient;

public class LogoutRequest {
    public static void setAuthToken(String authToken) {
        BaseClient.setAuthToken(authToken);
    }
}

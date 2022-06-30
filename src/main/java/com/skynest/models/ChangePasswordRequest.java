package com.skynest.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;

    public static ChangePasswordRequest generateValidChangePasswordRequest() {
        return ChangePasswordRequest.builder()
                .currentPassword("Hris8833")
                .newPassword("Hris8833")
                //TODO Change this with random generated password properties file for credentials is added
                .build();
    }
}

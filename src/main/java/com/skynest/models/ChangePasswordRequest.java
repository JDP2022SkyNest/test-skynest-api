package com.skynest.models;

import com.skynest.utils.RandomGenerator;
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
                .newPassword(RandomGenerator.generateStrongPassword())
                .build();
    }
}

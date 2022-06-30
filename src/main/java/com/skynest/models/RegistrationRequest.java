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
public class RegistrationRequest {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;

    public static RegistrationRequest generateValidRegistrationRequest() {
        return RegistrationRequest.builder()
                .email(RandomGenerator.generateRandomEmail())
                .password(RandomGenerator.generateStrongPassword())
                .name(RandomGenerator.generateRandomFirstName())
                .surname(RandomGenerator.generateRandomLastName())
                .phoneNumber(RandomGenerator.generateRandomPhoneNumber())
                .address(RandomGenerator.generateRandomAddress())
                .build();

    }
}

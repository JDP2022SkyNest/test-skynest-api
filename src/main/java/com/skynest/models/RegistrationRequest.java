package com.skynest.models;

import com.skynest.utils.RandomGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;

    public static RegistrationRequest generateValidRegistrationRequest() {

        return new RegistrationRequest(RandomGenerator.generateRandomEmail(),
                "Example123451",
                RandomGenerator.generateRandomFirstName(),
                RandomGenerator.generateRandomLastName(),
                RandomGenerator.generateRandomPhoneNumber(),
                RandomGenerator.generateRandomAddress());
    }
}


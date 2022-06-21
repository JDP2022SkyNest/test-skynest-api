package com.skynest.tests.files;

import com.github.javafaker.Faker;
import com.skynest.tests.utils.RandomGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private static final Faker faker = new Faker();
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;

    public static RegistrationRequest generateValidRegistrationRequest() {
        String randomPhoneNumber = faker.phoneNumber().phoneNumber().replaceAll("-", "").replaceAll("\\.", "");
        return new RegistrationRequest(RandomGenerator.generateRandomEmail(), "Example123451",
                faker.name().firstName(),
                faker.name().lastName(),
                randomPhoneNumber,
                faker.address().fullAddress());
    }
}


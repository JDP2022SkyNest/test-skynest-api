package com.skynest.tests.api;

import com.github.javafaker.Faker;
import com.skynest.tests.utils.RandomGenerator;
import lombok.*;

@Setter
@Getter
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
        Faker faker = new Faker();
        String randomPhoneNumber = String.valueOf(RandomGenerator.getRandomNumberFromTo(100000, 999999));
        return RegistrationRequest.builder()
                .email(RandomGenerator.generateRandomEmail())
                .password("Example123451")
                .name(faker.name().firstName())
                .surname(faker.name().lastName())
                .phoneNumber(randomPhoneNumber)
                .address(faker.address().fullAddress())
                .build();
    }
}


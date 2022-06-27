package com.skynest.utils;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomGenerator {
    private static final Faker FAKER = new Faker();

    public static String generateRandomEmail() {
        return "myemail" + System.nanoTime() + "@gmail.com";
    }

    public static String generateRandomPhoneNumber() {
        return FAKER.phoneNumber().phoneNumber()
                .replace("-", "")
                .replace("\\.", "")
                .replace("\\(", "")
                .replace("\\)", "").
                replace(" ", "");
    }

    public static String generateRandomFirstName() {
        return FAKER.name().firstName();
    }

    public static String generateRandomLastName() {
        return FAKER.name().lastName();
    }

    public static String generateRandomAddress() {
        return FAKER.address().fullAddress();
    }

}

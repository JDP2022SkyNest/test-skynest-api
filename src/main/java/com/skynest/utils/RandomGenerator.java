package com.skynest.utils;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomGenerator {
    private static final Faker faker = new Faker();

    public static String generateRandomEmail() {
        return "myemail" + System.nanoTime() + "@gmail.com";
    }

    public static String generateRandomPhoneNumber() {
        return faker.phoneNumber().phoneNumber()
                .replaceAll("-", "")
                .replaceAll("\\.", "")
                .replaceAll("\\(", "")
                .replaceAll("\\)", "").
                replaceAll(" ", "");
    }

    public static String generateRandomFirstName() {
        return faker.name().firstName();
    }

    public static String generateRandomLastName() {
        return faker.name().lastName();
    }

    public static String generateRandomAddress() {
        return faker.address().fullAddress();
    }

}

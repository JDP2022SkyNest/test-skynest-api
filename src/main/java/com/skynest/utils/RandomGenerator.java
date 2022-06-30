package com.skynest.utils;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.util.Objects.nonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomGenerator {
    private static final Faker FAKER = new Faker();

    public static String generateRandomEmail() {
        return "myemail" + System.nanoTime() + "@gmail.com";
    }

    public static String generateRandomPhoneNumber() {
        return FAKER.phoneNumber().phoneNumber()
                .replace("-", "")
                .replace(".", "")
                .replace("(", "")
                .replace(")", "")
                .replace("x", "")
                .replace(" ", "");
    }

    public static String generateRandomFirstName() {
        return FAKER.name().firstName();
    }

    public static String generateRandomLastName() {
        return FAKER.name().lastName();
    }

    public static String generateStrongPassword() {
        var password = new Faker().internet().password(8,30 , true, false, true);
        if (isPasswordValid(password)) {
            return password;
        }
        return generateStrongPassword();
    }

    private static boolean isPasswordValid(String password) {
        return nonNull(password) && password.length() >= 8 &&
                password.chars().anyMatch(Character::isDigit) &&
                password.chars().anyMatch(Character::isLowerCase) &&
                password.chars().anyMatch(Character::isUpperCase);
    }

    public static String generateRandomAddress() {
        return FAKER.address().fullAddress();
    }

}

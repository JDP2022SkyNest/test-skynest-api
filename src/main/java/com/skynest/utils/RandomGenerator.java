package com.skynest.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomGenerator {
    public static String generateRandomEmail() {
        return "myemail" + System.nanoTime() + "@gmail.com";
    }
}

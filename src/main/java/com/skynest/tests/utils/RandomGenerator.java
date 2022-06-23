package com.skynest.tests.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomGenerator {

    public static String generateRandomEmail() {
        return "myemail" + System.nanoTime() + "@gmail.com";
    }

    public static Integer getRandomNumberFromTo(int min, int max) {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        return min + threadLocalRandom.nextInt(max);
    }
}

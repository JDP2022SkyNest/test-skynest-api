package com.skynest.tests.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BaseHelper {
    public static String generateRandomEmail() {
        String myEmailAddress = "myemail" + System.nanoTime() + "@gmail.com";
        return myEmailAddress;
    }
}

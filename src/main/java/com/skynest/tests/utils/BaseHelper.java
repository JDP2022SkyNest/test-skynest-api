package com.skynest.tests.utils;

public class BaseHelper {
    public String generateRandomEmail(){
        String myEmailAddress = "myemail" + System.nanoTime() + "@gmail.com";
        return myEmailAddress;
    }
}

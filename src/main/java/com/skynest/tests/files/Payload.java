package com.skynest.tests.files;

public class Payload {
    public static String userRegistrationData(String email, String pass, String name, String surname, String phoneNo, String address){
        String payload = "{\n" +
                "  \"email\": \""+email+"\",\n" +
                "  \"password\": \""+pass+"\",\n" +
                "  \"name\": \""+name+"\",\n" +
                "  \"surname\": \""+surname+"\",\n" +
                "  \"phoneNumber\": \""+phoneNo+"\",\n" +
                "  \"address\": \""+address+"\"\n" +
                "}";
        return payload;
    }
}

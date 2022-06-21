package com.skynest.tests.files;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RegistrationResponse {
    private final String id;
    private final String email;
    private final String name;
    private final String surname;
    private final String phoneNumber;
    private final String address;
    private final String roleName;

}
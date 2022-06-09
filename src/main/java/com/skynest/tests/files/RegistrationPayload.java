package com.skynest.tests.files;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationPayload {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
}

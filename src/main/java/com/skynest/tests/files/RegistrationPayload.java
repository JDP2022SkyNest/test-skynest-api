package com.skynest.tests.files;

import lombok.*;
@Setter @Getter
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

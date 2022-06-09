package com.skynest.tests.files;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class RegistrationResponse {
    private String id;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;

}

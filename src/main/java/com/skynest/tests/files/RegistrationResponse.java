package com.skynest.tests.files;
import lombok.*;
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor

public class RegistrationResponse {
    private String id;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;

}

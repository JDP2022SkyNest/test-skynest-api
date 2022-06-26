package com.skynest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {
    private String id;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
    private String roleName;
    private boolean verified;
    private boolean enabled;
}

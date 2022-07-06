package com.skynest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String positionInCompany;
    private String companyName;
    private boolean verified;
    private boolean enabled;
}

package com.skynest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UUID id;
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

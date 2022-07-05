package com.skynest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EditResponse {
    private String id;
    private String email;
    private String name;
    private String surname;
    private String phoneNumber;
    private String address;
    private String roleName;
    private String positionInCompany;
    private String companyName;
    private boolean enabled;
    private boolean verified;
}

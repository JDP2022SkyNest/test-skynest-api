package com.skynest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoggedUserResponse {
    private UUID uuid;
    private String username;
    private String name;
    private String surname;
    private String positionInCompany;
    private String company;
    private List<String> roles;
}

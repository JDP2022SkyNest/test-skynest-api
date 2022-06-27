package com.skynest.tests.files;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}

package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private String domain;
    private String scope;

}

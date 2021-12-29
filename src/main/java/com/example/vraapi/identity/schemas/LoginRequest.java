package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
    private String domain;
    private String scope;

}

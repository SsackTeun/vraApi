package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class AuthorizationRequest {
    private String[] grant_type;
    private String refresh_token;
    private String code;
    private String state;
    private String redirect_uri;
    private String client_id;
    private String client_secret;
    private String scope;
    private String orgId;
}

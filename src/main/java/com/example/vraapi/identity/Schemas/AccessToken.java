package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class AccessToken {
    private String scope;
    private String access_token;
    private String refresh_token;
    private String id_token;
    private String token_type;
    private int expires_in;
}

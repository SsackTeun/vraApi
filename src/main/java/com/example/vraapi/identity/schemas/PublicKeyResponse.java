package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class PublicKeyResponse {
    private String alg;
    private String value;
    private String issuer;
    private String[] keys;
}

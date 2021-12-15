package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class ClientAndSecretResponse {
    private String clientId;
    private String clientSecret;
}

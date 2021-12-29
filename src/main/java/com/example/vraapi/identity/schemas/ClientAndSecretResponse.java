package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class ClientAndSecretResponse {
    private String clientId;
    private String clientSecret;
}

package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class UserLocaleRequest {
    private String locale;
    private String language;
}

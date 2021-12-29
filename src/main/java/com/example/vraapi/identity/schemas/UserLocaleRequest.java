package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class UserLocaleRequest {
    private String locale;
    private String language;
}

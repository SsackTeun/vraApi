package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class UserShortInfoResponse {
    private String email;
    private String locale;
    private String language;
}

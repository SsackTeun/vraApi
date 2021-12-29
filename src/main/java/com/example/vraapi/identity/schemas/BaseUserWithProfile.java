package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class BaseUserWithProfile {
    private String username;//deprecated
    private String firstName;
    private String lastName;
    private String domain;
    private String idpId;
    private String accessible;
    private String acct;
    private String email;
    private String userId;
    private UserLocalePreferences[] userProfile;
}

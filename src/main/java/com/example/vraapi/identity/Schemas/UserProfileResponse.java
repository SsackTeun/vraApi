package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class UserProfileResponse {
    private String defaultOrgId;
    private boolean isFederated;
    private String linkedUserIdAccount;
    private String customerNumber;
    private String locale;
    private String language;
    private String userId;
    private String username;
    private String deprecated;
    private String acct;
    private String email;
    private UserProfileMetadata userProfileMetadata;
    private int createdAt;
}

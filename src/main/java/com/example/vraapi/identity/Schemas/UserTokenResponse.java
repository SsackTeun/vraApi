package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class UserTokenResponse {
    private String username;
    private String email;
    private String domain;
    private String acct;
    private String context;
    private String sub;
    private String given_name;
    private String family_name;
    private boolean email_verified;
    private String[] group_names;
    private String[] group_ids;
    private String context_name;
}

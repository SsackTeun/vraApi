package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class IdTokenResponse {
    private String username;
    private String email;
    private String domain;
    private String acct;
    private String context;
    private String sub;
    private String iss;
    private int lat;
    private int exp;
    private String[] aud;
    private String given_name;
    private String family_name;
    private boolean email_verified;
    private String[] group_names;
    private String[] group_ids;
    private String[] context_name;
    private int auth_time;
}

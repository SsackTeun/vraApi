package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String username; //deprecated
    private String acct;
    private String password;
    private String email;
    private String refLink;
    private String[] groups;
    private LeanUserProfile userProfile;
    private String managerId;
}

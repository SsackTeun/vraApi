package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class UserByUserIdResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String acct;
    private String password;
    private String email;
    private String refLink;
    private String[] groups;
    private LeanUserProfile userProfiles;
    private String managerId;
    private IdpUserDetails idpUserDetails;
}

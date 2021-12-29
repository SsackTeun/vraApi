package com.example.vraapi.identity.schemas;

public class OrgOAuthAppRequest {

    private String id;
    private String secret;
    private String displayName;
    private String description;
    private String[] redirectUris;
    private String[] grantTypes;
    private int accessTokenTTL;
    private int refreshTokenTTL;
    private int maxGroupsInIdToken;
    private AllowedScopes allowedScopes;

}

package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class OrgOAuthAppResponse {
    private String id;
    private String displayName;
    private String description;
    private String[] redirectUris;
    private String[] grantTypes;
    private int accessTokenTTL;
    private int refreshTokenTTL;
    private int createdAt;
    private int lastUpdatedAt;
    private String organizationId;
    private int maxGroupsInIdToken;
    private OrganizationDetails[] allowedOrgs;
}

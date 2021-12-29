package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class ExpandedGroup {
    private String id;
    private String displayName;
    private String domain;
    private int usersCount;
    private OrganizationRole[] organizationRoles;
    private ServiceRoleNames[] serviceRoles;
}

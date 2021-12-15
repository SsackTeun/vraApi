package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class OrganizationRole {
    private String id;
    private String orgId;
    private String name;
    private String displayName;
}

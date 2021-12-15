package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class ExpandedTypedUser {
    private BaseUserWithProfile user;
    private String orgId;
    private TypedRole[] organizationRoles;
    private TypedServiceRoles[] serviceRoles;
}

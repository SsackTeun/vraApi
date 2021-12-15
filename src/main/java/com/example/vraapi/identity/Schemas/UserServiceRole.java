package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class UserServiceRole {
    private String serviceDefinitionLink;
    private String[] serviceRoleNames;
    private ServiceRoleBinding[] serviceRoles;
}

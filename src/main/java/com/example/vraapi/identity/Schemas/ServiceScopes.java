package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class ServiceScopes {
    private boolean allRoles;
    private String[] roleNames;
    private ServiceScope[] roles;
    private String serviceDefinitionId;
}

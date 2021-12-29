package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class ServiceScopes {
    private boolean allRoles;
    private String[] roleNames;
    private ServiceScope[] roles;
    private String serviceDefinitionId;
}

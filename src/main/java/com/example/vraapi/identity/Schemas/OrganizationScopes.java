package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class OrganizationScopes {
    private boolean allRoles;
    private String[] roleNames;
    private BaseScope[] roles;
}

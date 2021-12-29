package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class OrganizationScopes {
    private boolean allRoles;
    private String[] roleNames;
    private BaseScope[] roles;
}

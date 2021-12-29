package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class AllowedScopes {
    private boolean allRoles;
    private ServiceScopes[] servicesScopes;
    private OrganizationScopes[] organizationScopes;
    private String[] generalScopes;
}

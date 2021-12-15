package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class AllowedScopes {
    private boolean allRoles;
    private ServiceScopes[] servicesScopes;
    private OrganizationScopes[] organizationScopes;
    private String[] generalScopes;
}

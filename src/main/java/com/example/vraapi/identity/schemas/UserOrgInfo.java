package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class UserOrgInfo {
    private String displayName;
    private String name;
    private Role[] orgRoles;
    private ServiceDefInfo[] serviceDef;
}

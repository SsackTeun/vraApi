package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class UserWithDesiredOrgRole {
    private String userName;
    private UpdateRolesRequestBaseRoleBinding[] orgRolesUpdateRequest;
}

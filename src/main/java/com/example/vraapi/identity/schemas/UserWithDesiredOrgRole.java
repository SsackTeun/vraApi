package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class UserWithDesiredOrgRole {
    private String userName;
    private UpdateRolesRequestBaseRoleBinding[] orgRolesUpdateRequest;
}

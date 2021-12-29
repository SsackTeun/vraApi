package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class UserServiceRolesUpdateRequest {
    private ServiceRoleBinding[] rolesToAdd;
    private ServiceRoleBinding[] rolesToRemove;
    private String serviceDefinitionLink;
}

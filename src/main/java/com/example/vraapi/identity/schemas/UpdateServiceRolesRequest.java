package com.example.vraapi.identity.schemas;

public class UpdateServiceRolesRequest {
    private BaseRoleBinding[] rolesToAdd;
    private BaseRoleBinding[] rolesToRemove;
    private String serviceDefinitionId;
}

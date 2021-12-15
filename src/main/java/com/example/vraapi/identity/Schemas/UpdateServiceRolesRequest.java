package com.example.vraapi.identity.Schemas;

public class UpdateServiceRolesRequest {
    private BaseRoleBinding[] rolesToAdd;
    private BaseRoleBinding[] rolesToRemove;
    private String serviceDefinitionId;
}

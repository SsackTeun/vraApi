package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class UserServiceRolesPatchRequest {
    private String serviceId;
    private String[] roleNamesToAdd;
    private String[] roleNamesToRemove;
}

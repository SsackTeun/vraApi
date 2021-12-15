package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class UserServicesRolesPatchRequest {
    private UserServiceRolesPatchRequest[] serviceRolesPatchRequest;
}

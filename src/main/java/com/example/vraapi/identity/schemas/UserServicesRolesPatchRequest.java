package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class UserServicesRolesPatchRequest {
    private UserServiceRolesPatchRequest[] serviceRolesPatchRequest;
}

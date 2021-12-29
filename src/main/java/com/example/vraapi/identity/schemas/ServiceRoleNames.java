package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class ServiceRoleNames {
    private String serviceDefinitionId;
    private String[] serviceRoleNames;
}

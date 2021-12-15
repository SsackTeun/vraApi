package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class ServiceRoleResponse {
    private String name;
    private String displayName;
    private ServiceDefinitionResponse[] serviceDefinition;
    private String serviceDefinitionLink;
    private String[] userIds;
    private boolean isDefault;
    private boolean isHidden;
}

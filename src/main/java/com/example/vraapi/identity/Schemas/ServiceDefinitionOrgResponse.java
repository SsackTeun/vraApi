package com.example.vraapi.identity.Schemas;

import lombok.Data;

import java.util.Map;

@Data
public class ServiceDefinitionOrgResponse {
    private String name;
    private String displayName;
    private Map<String, String> metadata;
    private String orgId;
    private String parentOrgId;
}

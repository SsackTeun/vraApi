package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class AllServiceDefinitionsResponse {
    private String[] serviceDefinitionLinks;
    private ServiceDefinitionResponse[] results;
}

package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class AllServiceDefinitionsResponse {
    private String[] serviceDefinitionLinks;
    private ServiceDefinitionResponse[] results;
}

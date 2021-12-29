package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class TypedServiceRoles {
    private String serviceDefinitionId;
    private TypedServiceRole[] serviceRoles;
}

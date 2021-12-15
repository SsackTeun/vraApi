package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class TypedServiceRoles {
    private String serviceDefinitionId;
    private TypedServiceRole[] serviceRoles;
}

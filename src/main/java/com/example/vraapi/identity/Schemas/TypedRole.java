package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class TypedRole {
    private String id;
    private String orgId;
    private String name;
    private String displayName;
    private String[] membershipType;
}

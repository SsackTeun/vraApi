package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class TypedServiceRole {
    private String name;
    private String[] membershipType;
    private String resource;
    private String roleDisplayName;
}

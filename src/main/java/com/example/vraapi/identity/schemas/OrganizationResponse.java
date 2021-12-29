package com.example.vraapi.identity.schemas;

import lombok.Data;

import java.util.Map;

@Data
public class OrganizationResponse {
    private String name;
    private String displayName;
    private Map<String, String> metadata;
    private String id;
    private String refLink;
    private String parentRefLink;
}

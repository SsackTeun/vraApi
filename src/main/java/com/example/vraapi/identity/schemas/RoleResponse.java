package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class RoleResponse {
    private String refLink;
    private String name;
    private String displayName;
    private String organizationLink;
    private boolean visible;
}

package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class Role {
    private String id;
    private int createdMillis;
    private int updatedMillis;
    private String name;
    private String displayName;
    private String orgId;
    private boolean visible;
    private String organizationLink;
    private String refLink;
    private String[] userIds;
    private String[] groupIds;
}
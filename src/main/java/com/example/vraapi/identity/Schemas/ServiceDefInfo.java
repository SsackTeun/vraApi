package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class ServiceDefInfo {
    private String refLink;
    private String serviceDisplayName;
    private String serviceName;
    private String[] serviceRoles;
}

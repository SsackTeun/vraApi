package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class ServiceDefinitionResponse {
    private String name;
    private String displayName;
    private String descriptionLong;
    private boolean isGated;
    private boolean visible;
    private ServiceUrls[] serviceUrls;
    private ServiceRoleResponse[] serviceRoles;
    private String organizationLink;
    private String documentSelfLink;
    private String healthCheckURL;
    private String serviceIcon;
    private String serviceNavBarIcon;
    private boolean isPrimary;
    private boolean isBeta;
}

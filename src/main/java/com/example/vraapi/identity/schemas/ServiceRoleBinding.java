package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class ServiceRoleBinding {
    private String name;
    private String membershipType;
    private String resource;
}

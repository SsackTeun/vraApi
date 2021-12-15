package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class Organizations {
    private String[] refLinks;
    private OrganizationResponse[] items;
}

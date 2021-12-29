package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class Organizations {
    private String[] refLinks;
    private OrganizationResponse[] items;
}

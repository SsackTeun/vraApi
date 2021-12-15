package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class PagedResponseOrganizationResponse {
    private OrganizationResponse[] results;
    private String nextLink;
    private String prevLink;
    private int totalResults;
}

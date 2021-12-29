package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class PagedResponseServiceDefinitionOrgResponse {
    private ServiceDefinitionOrgResponse[] results;
    private String nextLink;
    private String prevLink;
    private int totalResults;
}

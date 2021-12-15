package com.example.vraapi.identity.Schemas;

import lombok.Data;

import java.util.List;

@Data
public class PagedResponseServiceDefinitionResponse {
    private List<ServiceDefinitionResponse> results;
    private String nextLink;
    private String prevLink;
    private int totalResults;
}

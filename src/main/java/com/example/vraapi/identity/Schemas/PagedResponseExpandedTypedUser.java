package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class PagedResponseExpandedTypedUser {
    private ExpandedTypedUser[] results;
    private String nextLink;
    private String prevLink;
    private int totalResults;
}

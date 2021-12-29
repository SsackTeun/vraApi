package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class PagedResponseGroup {
    private Group[] results;
    private String nextLink;
    private String prevLink;
    private int totalResults;
}

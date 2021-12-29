package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class SearchGroupsResponse {
    private ExpandedGroup[] results;
}

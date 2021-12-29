package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class SearchUsersResponse {
    private ExpandedTypedUser[] results;
}

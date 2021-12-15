package com.example.vraapi.identity.Schemas;

import lombok.Data;

@Data
public class SearchUsersResponse {
    private ExpandedTypedUser[] results;
}

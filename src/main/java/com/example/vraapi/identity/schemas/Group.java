package com.example.vraapi.identity.schemas;

import lombok.Data;

@Data
public class Group {
    private String id;
    private String displayName;
    private String domain;
    private int userCount;
}

package com.example.vraapi.deployment.schemas;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class ResourceReference {
    private String description;
    private String id;
    private String name;
    private String version;
}

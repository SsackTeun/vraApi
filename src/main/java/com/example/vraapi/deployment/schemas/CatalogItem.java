package com.example.vraapi.deployment.schemas;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class CatalogItem {
    private int bulkRequestLimit;
    private String createdAt;
    private String createdBy;
    private String description;
    private String formId;
    private String iconId;
    private String id;
    private String lastUpdatedAt;
    private String lastUpdatedBy;
    private String name;
    private String projectIds;
    private ResourceReference projects[];
}

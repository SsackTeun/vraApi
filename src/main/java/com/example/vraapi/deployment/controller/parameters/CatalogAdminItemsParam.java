package com.example.vraapi.deployment.controller.parameters;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class CatalogAdminItemsParam {
    private String orderBy;
    private int skip;
    private int top;
    private String apiVersion;
    private String projectId;
    private String search;
    private String sourceIds[];
    private String types[];
}

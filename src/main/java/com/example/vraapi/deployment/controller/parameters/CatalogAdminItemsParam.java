package com.example.vraapi.deployment.controller.parameters;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Required;

@Slf4j
@Data
public class CatalogAdminItemsParam {
    private String orderBy;
    private String skip;
    private String top;
    private String apiVersion;
    private String projectId;
    private String search;
    private String sourceIds[];
    private String types[];
}

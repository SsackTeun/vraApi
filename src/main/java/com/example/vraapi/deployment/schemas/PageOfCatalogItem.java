package com.example.vraapi.deployment.schemas;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class PageOfCatalogItem {
    private CatalogItem content[];
    private boolean empty;
    private boolean first;
    private boolean last;
    private int number;
    private int numberOfElements;
    private int size;
    private Sort sort;
    private long totalElements;
    private int totalPages;
}

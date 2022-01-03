package com.example.vraapi.deployment.service;

import com.example.vraapi.deployment.controller.parameters.CatalogAdminItemsParam;
import com.example.vraapi.deployment.schemas.PageOfCatalogItem;
import com.example.vraapi.util.APIUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatalogAdminItemsService {
    private final WebClient webClient;

    public ResponseEntity<PageOfCatalogItem> fetchListOfCatalogItems(CatalogAdminItemsParam catalogAdminItemsParam, String token){
        return new APIUtil<PageOfCatalogItem>(webClient, token).get(catalogAdminItemsParam, URI.create("/catalog/api/admin/items") , PageOfCatalogItem.class);
    }
}

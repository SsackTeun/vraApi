package com.example.vraapi.deployment.controller;

import com.example.vraapi.deployment.controller.parameters.CatalogAdminItemsParam;
import com.example.vraapi.deployment.schemas.PageOfCatalogItem;
import com.example.vraapi.deployment.service.CatalogAdminItemsService;
import com.example.vraapi.util.APIUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/catalog")
public class CatalogAdminItemsController {

    private CatalogAdminItemsService catalogAdminItemsService;

    CatalogAdminItemsController(CatalogAdminItemsService catalogAdminItemsService){
        this.catalogAdminItemsService = catalogAdminItemsService;
    }

    @RequestMapping("/api/admin/items")
    public ResponseEntity<PageOfCatalogItem> pageOfCatalogItemResponse(@ModelAttribute CatalogAdminItemsParam catalogAdminItemsParam){
        log.info("CatalogAdminItemsParam : " + catalogAdminItemsParam.toString());
        ResponseEntity<PageOfCatalogItem> CatalogAdminItems = catalogAdminItemsService.fetchListOfCatalogItems(catalogAdminItemsParam);
        return null;
    }
}

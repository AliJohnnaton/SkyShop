package com.skypro.skyshop.controller;

import com.skypro.skyshop.service.SearchService;
import com.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;

    public ShopController(StorageService storageService, SearchService searchService) {
        this.storageService = storageService;
        this.searchService = searchService;
    }

    @GetMapping("/products")
    public String getAllProducts() {
        return storageService.getAllProducts().toString();
    }

    @GetMapping("/articles")
    public String getAllArticles() {
        return storageService.getAllArticles().toString();
    }

    @GetMapping("/search")
    public String getSearchResult(@RequestParam("sub") String sub) {
        return searchService.search(sub).toString();
    }
}

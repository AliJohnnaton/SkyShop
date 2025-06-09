package com.skypro.skyshop.controller;

import com.skypro.skyshop.model.basket.UserBasket;
import com.skypro.skyshop.service.BasketService;
import com.skypro.skyshop.service.SearchService;
import com.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ShopController {
    private final StorageService storageService;
    private final SearchService searchService;
    private final BasketService basketService;

    public ShopController(StorageService storageService, SearchService searchService, BasketService basketService) {
        this.storageService = storageService;
        this.searchService = searchService;
        this.basketService = basketService;
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

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        try {
            basketService.addProduct(id);
        } catch (IllegalArgumentException e) {
            return "Продукт не найден";
        }
        return "Продукт успешно добавлен";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }
}

package com.skypro.skyshop.service;

import com.skypro.skyshop.model.basket.BasketItem;
import com.skypro.skyshop.model.basket.ProductBasket;
import com.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket basket;
    private final StorageService storage;

    public BasketService(ProductBasket basket, StorageService storage) {
        this.basket = basket;
        this.storage = storage;
    }

    public String addProduct(UUID id) {
        if (storage.getProductById(id).isEmpty()) {
            return "Продукт не найден";
        }
        basket.add(id);
        return "Продукт успешно добавлен";

    }

    public UserBasket getUserBasket() {
        return new UserBasket(basket.getBasket().entrySet().stream()
                .map(k -> new BasketItem(storage.getAllProducts().get(k.getKey()), k.getValue()))
                .collect(Collectors.toList()));

    }
}

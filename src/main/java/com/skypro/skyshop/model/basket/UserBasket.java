package com.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {
    private final List<BasketItem> basket;
    private final long total;

    public UserBasket(List<BasketItem> basket) {
        this.basket = basket;
        this.total = this.basket.stream()
                .mapToLong(k -> (long) k.getProduct().getPrice() * k.getCount())
                .sum();
    }
}

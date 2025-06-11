package com.skypro.skyshop.service;

import com.skypro.skyshop.exceptions.NoSuchProductException;
import com.skypro.skyshop.model.basket.BasketItem;
import com.skypro.skyshop.model.basket.ProductBasket;
import com.skypro.skyshop.model.basket.UserBasket;
import com.skypro.skyshop.model.products.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BasketServiceTest {

    private ProductBasket productBasket;
    private StorageService storageService;
    private BasketService basketService;

    @BeforeEach
    void setUp() {
        productBasket = mock(ProductBasket.class);
        storageService = mock(StorageService.class);
        basketService = new BasketService(productBasket, storageService);
    }

    @Test
    void addProduct_throwsException_whenProductNotFound() {
        UUID id = UUID.randomUUID();
        when(storageService.getProductById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(id));
    }

    @Test
    void addProduct_addsProduct_whenExists() {
        UUID id = UUID.randomUUID();
        Product product = mock(Product.class);

        when(storageService.getProductById(id)).thenReturn(Optional.of(product));
        basketService.addProduct(id);

        verify(productBasket, times(1)).add(id);
    }

    @Test
    void getUserBasket_returnsEmptyBasket_whenBasketIsEmpty() {
        when(productBasket.getBasket()).thenReturn(Collections.emptyMap());
        when(storageService.getAllProducts()).thenReturn(Collections.emptyMap());

        UserBasket basket = basketService.getUserBasket();
        assertTrue(basket.getBasket().isEmpty());
        assertEquals(0, basket.getTotal());
    }

    @Test
    void getUserBasket_returnsCorrectBasket_whenBasketIsFilled() {
        UUID id = UUID.randomUUID();
        Product product = mock(Product.class);
        when(product.getPrice()).thenReturn(100);

        when(productBasket.getBasket()).thenReturn(Map.of(id, 2));
        when(storageService.getAllProducts()).thenReturn(Map.of(id, product));

        UserBasket basket = basketService.getUserBasket();

        List<BasketItem> items = basket.getBasket();
        assertEquals(1, items.size());
        assertEquals(200, basket.getTotal());
    }
}

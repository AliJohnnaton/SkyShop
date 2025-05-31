package com.skypro.skyshop.service;

import com.skypro.skyshop.model.article.Article;
import com.skypro.skyshop.model.products.DiscountedProduct;
import com.skypro.skyshop.model.products.FixPriceProduct;
import com.skypro.skyshop.model.products.Product;
import com.skypro.skyshop.model.products.SimpleProduct;
import com.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;

    public StorageService() {
        this.products = new HashMap<>();
        Product bread = new SimpleProduct("Хлеб Красная цена", 24, UUID.randomUUID());
        Product milk = new DiscountedProduct("Молоко Счастливое детство", 69, 10, UUID.randomUUID());
        Product sausage = new FixPriceProduct("Колбаса Вязанка          ", UUID.randomUUID());
        Product sausagePAPA = new FixPriceProduct("Колбаса Папа Может", UUID.randomUUID());
        Product cheese = new SimpleProduct("Сыр Liebendorf", 220, UUID.randomUUID());
        Product chocolate = new DiscountedProduct("Шоколад Россия - Щедрая душа", 80, 50, UUID.randomUUID());
        Product chips = new SimpleProduct("Чипсы Lay\\'s", 175, UUID.randomUUID());
        Article sausageSecond = new Article("Колбаса <<Папа может>>", "Одна из тех кооооооооооолбас что мне нравиться", UUID.randomUUID());
        Article sausageThird = new Article("Колбаса <<Дугушка>>", "Постоянно её беру", UUID.randomUUID());

        this.products.put(bread.getId(), bread);
        this.products.put(milk.getId(), milk);
        this.products.put(sausage.getId(), sausage);
        this.products.put(sausagePAPA.getId(), sausagePAPA);
        this.products.put(cheese.getId(), cheese);
        this.products.put(chocolate.getId(), chocolate);
        this.products.put(chips.getId(), chips);

        this.articles = new HashMap<>();
        this.articles.put(sausageSecond.getId(), sausageSecond);
        this.articles.put(sausageThird.getId(), sausageThird);
    }

    public void createProduct(Product product) {
        final UUID tmpId = UUID.randomUUID();
        products.put(tmpId, product);
    }

    public void createArticle(Article article) {
        final UUID tmpId = UUID.randomUUID();
        articles.put(tmpId, article);
    }

    public Map<UUID, Article> getAllArticles() {
        return articles;
    }

    public Map<UUID, Product> getAllProducts() {
        return products;
    }

    public List<Searchable> getForSearch() {
        List<Searchable> result = new ArrayList<>(products.values());
        result.addAll(articles.values());
        return result;
    }
}

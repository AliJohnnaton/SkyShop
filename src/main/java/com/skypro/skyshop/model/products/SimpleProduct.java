package com.skypro.skyshop.model.products;

import com.skypro.skyshop.exceptions.IllegalPriceException;

import java.util.Objects;
import java.util.UUID;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(String name, int price, UUID id) {
        super(name, id);
        try {
            checkValue(price);
        } catch (IllegalPriceException e) {
            System.out.println("Указана не корректная цена");
            price = 1;
        } finally {
            this.price = price;
        }
    }

    private void checkValue(int value) {
        if (value <= 0)
            throw new IllegalPriceException();
    }

    @Override
    public String toString() {
        return super.toString() + price + "руб.";
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SimpleProduct that = (SimpleProduct) o;
        return price == that.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price);
    }
}

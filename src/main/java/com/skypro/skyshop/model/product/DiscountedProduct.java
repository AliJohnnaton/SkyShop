package com.skypro.skyshop.model.product;

import com.skypro.skyshop.exceptions.IllegalDiscountException;
import com.skypro.skyshop.exceptions.IllegalPriceAndDiscountException;
import com.skypro.skyshop.exceptions.IllegalPriceException;

import java.util.Objects;
import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int price;
    private final int discount;

    public DiscountedProduct(String name, int price, int discount, UUID id) {
        super(name, id);
        try {
            checkValues(price, discount);
        } catch (IllegalPriceAndDiscountException e) {
            System.out.println("Указана не корректная цена и скидка");
            price = 1;
            discount = 0;
        } catch (IllegalArgumentException e) {
            System.out.println("Указана не корректная цена");
            price = 1;
        } catch (RuntimeException e) {
            System.out.println("Указана некоректная скидка");
            discount = 0;
        } finally {
            this.price = price;
            this.discount = discount;
        }
    }

    private void checkValues(int price, int discount) {
        if (price <= 0 && (discount < 0 || discount > 100)) {
            throw new IllegalPriceAndDiscountException();
        } else if (price <= 0) {
            throw new IllegalPriceException();
        } else if (discount < 0 || discount > 100) {
            throw new IllegalDiscountException();
        }
    }

    @Override
    public String toString() {
        return super.toString() + price + "руб. Скидка: (" + discount + "%)";
    }

    @Override
    public int getPrice() {
        return price * (100 - discount) / 100;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscountedProduct that = (DiscountedProduct) o;
        return price == that.price && discount == that.discount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price, discount);
    }
}

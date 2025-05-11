package com.skypro.skyshop.model.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable, Comparable<Searchable> {
    private final UUID id;
    protected String name;

    Product(String name, UUID id) throws RuntimeException {
        this.id = id;
        try {
            checkName(name);
        } catch (NullPointerException n) {
            System.out.println("Была переданна null");
            name = "NULL";
        } catch (RuntimeException e) {
            System.out.println("Была переданная пустота");
            name = "Пустота";
        } finally {
            this.name = name;
        }
    }

    public abstract int getPrice();

    public abstract boolean isSpecial();

    @Override
    public String getName() {
        return name;
    }

    private void checkName(String name) {
        if (name == null)
            throw new NullPointerException();
        if (name.isEmpty())
            throw new RuntimeException();
    }

    @Override
    public String toString() {
        return "<" + name + " >";
    }

    @JsonIgnore
    @Override
    public String searchTerm() {
        return name;
    }

    @JsonIgnore
    @Override
    public String searchType() {
        return "PRODUCT";
    }

    @Override
    public String getStringRepresentation() {
        return searchTerm() + " - " + searchType();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public int compareTo(Searchable o) {
        return this.searchTerm().compareTo(o.searchTerm());
    }

    @Override
    public UUID getId() {
        return id;
    }
}

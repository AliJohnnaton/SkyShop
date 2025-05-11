package com.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable, Comparable<Searchable> {
    private final String name;
    private final String text;
    private final UUID id;

    public Article(String name, String text, UUID id) {
        this.name = name;
        this.text = text;
        this.id = id;
    }

    @Override
    public String toString() {
        return name + '\n' + text;
    }

    @JsonIgnore
    @Override
    public String searchTerm() {
        return toString();
    }

    @JsonIgnore
    @Override
    public String searchType() {
        return "ARTICLE";
    }

    @Override
    public String getStringRepresentation() {
        return searchTerm() + " - " + searchType();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Searchable o) {
        return this.searchTerm().compareTo(o.searchTerm());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Article article)) return false;
        return Objects.equals(name, article.name) && Objects.equals(text, article.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, text);
    }
}

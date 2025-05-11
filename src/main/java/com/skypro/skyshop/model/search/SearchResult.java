package com.skypro.skyshop.model.search;

import java.util.UUID;

public class SearchResult {
    private final UUID id;
    private final String name;
    private final String contentType;

    public SearchResult(Searchable o) {
        this.id = o.getId();
        this.name = o.getName();
        this.contentType = o.searchType();
    }

    public static SearchResult fromSearchable(Searchable o) {
        return new SearchResult(o);
    }

    @Override
    public String toString() {
        return "  ||" + id + "_____" + contentType + "_____" + name + "||";
    }
}

package com.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    String searchTerm();

    String searchType();

    String getStringRepresentation();

    UUID getId();

    String getName();
}

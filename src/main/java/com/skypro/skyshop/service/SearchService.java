package com.skypro.skyshop.service;

import com.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storage;

    public SearchService() {
        this.storage = new StorageService();
    }

    public ArrayList<SearchResult> search(String sub) {
        return storage.getForSearch().stream()
                .filter(Objects::nonNull)
                .filter(s -> s.searchTerm().toLowerCase().contains(sub))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

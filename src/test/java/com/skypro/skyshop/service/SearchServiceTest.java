package com.skypro.skyshop.service;

import com.skypro.skyshop.model.search.SearchResult;
import com.skypro.skyshop.model.search.Searchable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SearchServiceTest {

    private StorageService storageService;
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        storageService = mock(StorageService.class);
        searchService = new SearchService(storageService);
    }

    @Test
    void searchReturnsEmptyListWhenStorageIsEmpty() {
        when(storageService.getForSearch()).thenReturn(Collections.emptyList());

        List<SearchResult> result = searchService.search("milk");

        assertTrue(result.isEmpty());
    }

    @Test
    void searchReturnsEmptyListWhenNoMatchFound() {
        Searchable item = mock(Searchable.class);
        when(item.searchTerm()).thenReturn("cheese");

        when(storageService.getForSearch()).thenReturn(List.of(item));

        List<SearchResult> result = searchService.search("milk");

        assertTrue(result.isEmpty());
    }

    @Test
    void searchReturnsMatchWhenFound() {
        Searchable item = mock(Searchable.class);
        when(item.searchTerm()).thenReturn("milk");
        when(item.getStringRepresentation()).thenReturn("milk - PRODUCT");

        when(storageService.getForSearch()).thenReturn(List.of(item));

        List<SearchResult> result = searchService.search("milk");

        assertEquals(1, result.size());
        assertEquals("milk - PRODUCT", result.get(0).toString());
    }

    @Test
    void searchIsCaseInsensitive() {
        Searchable item = mock(Searchable.class);
        when(item.searchTerm()).thenReturn("Milk");
        when(item.getStringRepresentation()).thenReturn("Milk - PRODUCT");

        when(storageService.getForSearch()).thenReturn(List.of(item));

        List<SearchResult> result = searchService.search("milk");

        assertEquals(1, result.size());
        assertEquals("Milk - PRODUCT", result.get(0).toString());
    }

    @Test
    void searchReturnsMultipleMatches() {
        Searchable item1 = mock(Searchable.class);
        Searchable item2 = mock(Searchable.class);
        Searchable item3 = mock(Searchable.class);

        when(item1.searchTerm()).thenReturn("milk");
        when(item2.searchTerm()).thenReturn("milk chocolate");
        when(item3.searchTerm()).thenReturn("cheese");

        when(item1.getStringRepresentation()).thenReturn("milk - PRODUCT");
        when(item2.getStringRepresentation()).thenReturn("milk chocolate - PRODUCT");
        when(item3.getStringRepresentation()).thenReturn("cheese - PRODUCT");

        when(storageService.getForSearch()).thenReturn(List.of(item1, item2, item3));

        List<SearchResult> result = searchService.search("milk");

        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(r -> r.toString().contains("milk")));
        assertTrue(result.stream().noneMatch(r -> r.toString().contains("cheese")));
    }
}

package com.uem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearcherTestCase {

    private Searcher searcher;

    @BeforeEach
    void setUp() {
        searcher = new Searcher();
    }

    @Test
    void testSearchWord() {
        List<String> words = List.of("casa", "perro", "gato");

        assertTrue(searcher.searchWord("perro", words));
        assertFalse(searcher.searchWord("pez", words));
    }

    @Test
    void testGetWordByIndex() {
        List<String> words = List.of("primero", "segundo");

        assertEquals("segundo", searcher.getWordByIndex(words, 1));
        assertNull(searcher.getWordByIndex(words, -1));
        assertNull(searcher.getWordByIndex(words, 2));
    }

    @Test
    void testSearchByPrefix() {
        List<String> words = List.of("casa", "camino", "perro");

        assertEquals(List.of("casa", "camino"), searcher.searchByPrefix("ca", words));
        assertFalse(searcher.searchByPrefix("ca", words).contains("perro"));
    }

    @Test
    void testFilterByKeyword() {
        List<String> words = List.of("gato negro", "gato blanco", "perro");

        assertEquals(List.of("gato negro", "gato blanco"), searcher.filterByKeyword("gato", words));
        assertEquals(List.of(), searcher.filterByKeyword("pez", words));
    }

    @Test
    void testSearchExactPhrase() {
        List<String> phrases = List.of("primera frase", "frase buscada", "tercera frase");

        assertTrue(searcher.searchExactPhrase("primera frase", phrases));
        assertTrue(searcher.searchExactPhrase("frase buscada", phrases));
        assertFalse(searcher.searchExactPhrase("frase inexistente", phrases));
    }
}
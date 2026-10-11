package com.uem.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArticleTest {

    private static final double DELTA = 0.0001;

    @ParameterizedTest(name = "{0} uds x {1} = {2}")
    @CsvSource({
            "3, 10.0, 30.0",
            "1, 2.5, 2.5",
            "0, 99.9, 0.0",
            "4, 0.0, 0.0"
    })
    void testGetGrossAmount(int quantity, double price, double expected) {
        Article article = new Article("Item", quantity, price, 0.0);
        assertEquals(expected, article.getGrossAmount(), DELTA);
    }

    @ParameterizedTest(name = "{0} uds x {1} con {2}% = {3}")
    @CsvSource({
            "2, 50.0, 10.0, 90.0",
            "1, 100.0, 0.0, 100.0",
            "5, 20.0, 100.0, 0.0",
            "3, 9.99, 25.0, 22.4775"
    })
    void testGetDiscountedAmount(int quantity, double price, double discount, double expected) {
        Article article = new Article("Item", quantity, price, discount);
        assertEquals(expected, article.getDiscountedAmount(), DELTA);
    }

    @Test
    void testGetDiscountedAmountRejectsInvalidDiscount() {
        assertThrows(IllegalArgumentException.class,
                () -> new Article("Item", 1, 10.0, -5.0).getDiscountedAmount());
        assertThrows(IllegalArgumentException.class,
                () -> new Article("Item", 1, 10.0, 150.0).getDiscountedAmount());
    }

    @Test
    void testSettersRecalculateAmounts() {
        Article article = new Article();
        article.setName("Teclado");
        article.setQuantity(2);
        article.setPrice(40.0);
        article.setDiscount(50.0);

        assertEquals("Teclado", article.getName());
        assertEquals(80.0, article.getGrossAmount(), DELTA);
        assertEquals(40.0, article.getDiscountedAmount(), DELTA);
    }
}

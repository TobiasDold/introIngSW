package com.uem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderTest {

    private static final double DELTA = 0.0001;

    private Order order;

    @BeforeEach
    void setUp() {
        List<Article> articles = List.of(
                new Article("Ratón", 2, 15.0, 10.0),    // bruto 30.0  -> 27.0
                new Article("Monitor", 1, 200.0, 25.0), // bruto 200.0 -> 150.0
                new Article("Cable", 4, 2.5, 0.0)       // bruto 10.0  -> 10.0
        );
        order = new Order("PED-001", articles);
    }

    @Test
    void testGetGrossTotal() {
        assertEquals(240.0, order.getGrossTotal(), DELTA);
    }

    @Test
    void testGetDiscountedTotal() {
        assertEquals(187.0, order.getDiscountedTotal(), DELTA);
    }

    @Test
    void testEmptyOrderTotalsAreZero() {
        Order empty = new Order("PED-002", new ArrayList<>());
        assertEquals(0.0, empty.getGrossTotal(), DELTA);
        assertEquals(0.0, empty.getDiscountedTotal(), DELTA);
    }

    @Test
    void testNullArticlesTreatedAsEmpty() {
        Order nullOrder = new Order("PED-003", null);
        assertEquals(0.0, nullOrder.getGrossTotal(), DELTA);
    }

    @Test
    void testAddArticleUpdatesTotals() {
        order.addArticle(new Article("Alfombrilla", 1, 10.0, 50.0));
        assertEquals(250.0, order.getGrossTotal(), DELTA);
        assertEquals(192.0, order.getDiscountedTotal(), DELTA);
    }

    @Test
    void testDiscountedTotalNeverExceedsGrossTotal() {
        assertTrue(order.getDiscountedTotal() <= order.getGrossTotal());
    }

    @Test
    void testGettersAndSetters() {
        order.setId("PED-999");
        order.setArticles(List.of(new Article("Lápiz", 10, 0.5, 0.0)));

        assertEquals("PED-999", order.getId());
        assertEquals(1, order.getArticles().size());
        assertEquals(5.0, order.getGrossTotal(), DELTA);
    }

    @Test
    void testToStringContainsIdAndTotals() {
        String text = order.toString();
        assertTrue(text.contains("PED-001"));
        assertTrue(text.contains("grossTotal=240.0"));
        assertTrue(text.contains("discountedTotal=187.0"));
    }
}

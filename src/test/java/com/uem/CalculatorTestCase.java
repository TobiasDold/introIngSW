package com.uem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTestCase {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testMultiply() {
        int result = calculator.multiply(3, 4);
        int result2 = calculator.multiply(0, 3);
        int result3 = calculator.multiply(-2, 3);
        assertEquals(12, result);
        assertEquals(0, result2);
        assertEquals(-6, result3);
    }

    @Test
    void testConcat() {
        assertEquals("Hola mundo", calculator.concat("Hola ", "mundo"));
        assertEquals(Calculator.EMPTY, calculator.concat("Hola", null));
        assertEquals(Calculator.EMPTY, calculator.concat(null, "mundo"));
    }

    @Test
    void testSum() {
        assertEquals(5.0, calculator.sum(2.0, 3.0), 0.0001);
        assertEquals(-5.0, calculator.sum(-2.0, -3.0), 0.0001);
    }

    @Test
    void testDiscount() {
        assertEquals(90.0, calculator.discount(100.0, 10.0), 0.0001);
        assertEquals(100.0, calculator.discount(100.0, 0.0), 0.0001);
        assertEquals(0.0, calculator.discount(100.0, 100.0), 0.0001);
    }

    @Test
    void testDiscountRejectsInvalidPercentages() {
        assertThrows(IllegalArgumentException.class, () -> calculator.discount(100.0, -1.0));
        assertThrows(IllegalArgumentException.class, () -> calculator.discount(100.0, 101.0));
    }

    @Test
    void testCalculateTotal() {
        assertEquals(10.5, calculator.calculateTotal(java.util.List.of(2.0, 3.5, 5.0)), 0.0001);
        assertEquals(0.0, calculator.calculateTotal(java.util.List.of()), 0.0001);
    }
}
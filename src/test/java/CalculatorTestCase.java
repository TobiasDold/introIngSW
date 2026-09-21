import org.junit.jupiter.api.Test;

import com.uem.Calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;


public class CalculatorTestCase {

    private Calculator calculator;

    @BeforeEach
    void setUp(){
        calculator = new Calculator();
    }
    
    @Test
    void testMultiply(){
        int result = calculator.multiply(3, 4);
        int result2 = calculator.multiply(0, 3);
        int result3 = calculator.multiply(-2, 3);
        assertEquals(12, result);
        assertEquals(0, result2);
        assertEquals(-6, result3);
    }

    @Test 
    void testConcatNull(){
        String result = calculator.concat("Hola", null);
        assertEquals(Calculator.EMPTY, result);
    }
}

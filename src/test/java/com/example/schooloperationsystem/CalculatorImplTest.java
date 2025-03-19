package com.example.schooloperationsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorImplTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new CalculatorImpl();
    }

    @Test
    void add(){
        String a = "1";
        String b = "2";

        String sum = calculator.sum(a, b);

        assertEquals("3", sum);
    }

    @Test
    void add_NaN(){
        String a = "1234";
        String b = "123x";

        String sum = calculator.sum(a, b);

        assertEquals("NaN", sum);
    }

}
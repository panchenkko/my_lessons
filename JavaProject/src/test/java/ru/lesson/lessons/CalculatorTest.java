package ru.lesson.lessons;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void testAddition() throws Exception {
        Calculator calculator = new Calculator();
        calculator.addition(1, 1);
        assertEquals(calculator.getResult(), calculator.getResult());
    }
}
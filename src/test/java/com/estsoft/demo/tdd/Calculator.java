package com.estsoft.demo.tdd;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Calculator {

    @Test
    public void testSum() {
        //sum(1, 2) == 3
        assertEquals(3, sum(1, 2));
        assertEquals(100, sum(50, 50));
        assertEquals(-100, sum(-50, -50));
    }

    private int sum(int a, int b) {
        return a + b;
    }
}

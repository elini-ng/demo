package com.estsoft.demo;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AssertJTest {
    @Test
    public void addTest(){
        int a = 1;
        int b = 2;
        int result = 3;

        assertThat(a + b).isEqualTo(result);
    }
}

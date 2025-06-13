package ru.itis.inf403;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestTask1 {

    @Test
    void testSort() {
        assertArrayEquals(new byte[] {1,1,2,2,3,3,4,4,4,4,4,5,6,6,6,7,7,7,8,8,8,8},Task1.sort(new byte[] {1,2,3,4,5,6,7,8,1,2,3,4,4,4,4,8,8,8,7,7,6,6}));
    }



}

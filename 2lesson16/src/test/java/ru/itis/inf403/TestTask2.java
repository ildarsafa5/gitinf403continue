package ru.itis.inf403;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestTask2 {

    @Test
    void testFrequency() {
        assertArrayEquals(new int[] {4,7,0,0,2,4}, Task2.frequency(new byte[] {4,0,1,0,5,5,5,0,5,0,1,1,1,1,1,1,4},6));
    }
}

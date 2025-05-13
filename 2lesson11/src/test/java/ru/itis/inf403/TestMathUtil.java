package ru.itis.inf403;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestMathUtil {

    static MathUtil mathUtil;

    @BeforeAll
    static void init() {
        mathUtil = new MathUtil();
    }

    @Test
    void testSum() {
        int expected = 10; //ожидаемое значение
        int actual = MathUtil.sum(5, 5);

        assertEquals(expected, actual);

        assertEquals(0, MathUtil.sum(-5, 5));
        assertEquals(-1, MathUtil.sum(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }

    @ParameterizedTest
    @CsvFileSource(files = "test_data.csv")
    void testSum1(int a, int b, int expectedResult) {
        assertEquals(expectedResult, MathUtil.sum(a, b));
    }

    @Test
    void testCompare() {
        assertTrue(MathUtil.compare(5, 4) > 0);
        assertTrue(MathUtil.compare(4, 4) == 0);
        assertTrue(MathUtil.compare(4, 9) < 0);
    }

    @Test
    void testDivide() {
        assertThrows(IllegalArgumentException.class,
                () -> MathUtil.divide(7, 0));
    }

    @Test
    void testSort() {
        assertArrayEquals(new int[] {1,2,3,4,5,6,7,8},mathUtil.sort(new int[] {1,4,5,6,2,3,8,7}));
    }

    @AfterAll
    static void destroy() {

    }

}

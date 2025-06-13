package ru.itis.inf403;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestTask3 {

    @Test
    void testCopy() {
        byte[] src = {4,3,6,-8,43,17,-13,65,-34};
        ByteArrayInputStream bais = new ByteArrayInputStream(src);
        ByteArrayOutputStream baos = Task3.copy(bais);
        byte[] dst = baos.toByteArray();
        assertArrayEquals(src,dst);
    }
}

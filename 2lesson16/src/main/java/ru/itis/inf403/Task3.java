package ru.itis.inf403;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Task3 {
    public static ByteArrayOutputStream copy(ByteArrayInputStream bais) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        while ((i = bais.read()) != -1) {
            baos.write(i);
        }
        return baos;
    }

    public static void main(String[] args) {
        String text = "Hello, World";
        ByteArrayInputStream bais = new ByteArrayInputStream(text.getBytes());
        ByteArrayOutputStream baos = copy(bais);
        try (FileOutputStream fos = new FileOutputStream("simferopol")) {
            fos.write(baos.toByteArray());
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

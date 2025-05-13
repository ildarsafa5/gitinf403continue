package ru.itis.inf403;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainFileRead {
    public static void main(String[] args) {
        try (InputStream is = new FileInputStream("bookings.json")) {
            int i;
            long start = System.nanoTime();
            while ((i = is.read()) != -1) {
                System.out.print((char)i);
            }
            long end = System.nanoTime();
            System.out.println(end - start);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream is = new FileInputStream("bookings.json")) {
            byte[] buffer = new byte[1024];
            int i;  //количество реально прочитанных байтов
            long start = System.nanoTime();
            while ((i = is.read(buffer)) != -1) {
                System.out.print(new String(buffer, 0, i));
            }
            long end = System.nanoTime();
            System.out.println(end - start);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package ru.itis.inf403;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainFileWrite {
    public static void main(String[] args) {
        try(OutputStream fos = new FileOutputStream("test.txt")) {
            String[] array = {"ававвывм","fsgd","gsgs","ssgfg"};

            for(String n : array) {
                fos.write(n.getBytes(StandardCharsets.UTF_8));
            }
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

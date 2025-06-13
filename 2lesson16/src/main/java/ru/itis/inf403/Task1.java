package ru.itis.inf403;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Task1 {
    public static void confusion(String filename,String newfile) {
        try(FileInputStream fis = new FileInputStream(filename); FileOutputStream fos = new FileOutputStream(newfile)) {
            byte[] array = fis.readAllBytes();
            sort(array);
            fos.write(array);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] sort(byte[] array) {
        int[] c = new int[256];
        for (int i = 0; i < array.length; i++) {
            c[array[i]+128]++;
        }
        int k = 0;
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i]; j++) {
                array[k] = (byte) i;
                k++;
            }
        }
        return array;
    }
}

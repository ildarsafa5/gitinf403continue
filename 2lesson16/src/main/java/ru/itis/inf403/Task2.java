package ru.itis.inf403;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Task2 {

    public static void main(String[] args) {
        diagram("ildar.txt");
    }
    public static ByteArrayOutputStream diagram(String filename) {
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream();FileInputStream fis = new FileInputStream(filename)) {
            int i;
            while((i = fis.read())!=-1) {
                baos.write(i);
            }
            byte[] array = baos.toByteArray();
            int[] c = frequency(array,256);
            out(c);
            baos.write(array);
            baos.flush();
            return baos;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int[] frequency(byte[] current,int p) {
        int[] c = new int[p];
        for (int i = 0; i < current.length; i++) {
            c[current[i]+128]++;
        }
        return c;
    }

    public static void out(int[] freq) {
        int[] array = freq;
        for(int i = 0; i < 256; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        int max = 0;
        int pos = 0;
        for (int i = 0; i < freq.length; i++) {
            if (array[i] > max) {
                max = array[i];
                pos = i;
            }
        }
        String r = "\u2588";
        while (array[pos] > 0) {
            for (int i = 0; i < freq.length; i++) {
                if (array[i] > 0) {
                    if (i < 10) {
                        System.out.print(r + " ");
                    } else if (i >= 10) {
                        System.out.print(r + "  ");
                    } else if (i >= 100) {
                        System.out.print(r + "   ");
                    }
                    array[i]--;
                } else {
                    if (i < 10) {
                        System.out.print("  ");
                    } else if(i >= 10) {
                        System.out.print("   ");
                    } else if (i >= 100) {
                        System.out.print("    ");
                    }
                }
            }
            System.out.println();
        }
    }

}

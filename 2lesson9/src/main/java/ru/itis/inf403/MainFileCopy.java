package ru.itis.inf403;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MainFileCopy {
    private String inputFile;
    private String outputFile;
    public static void main(String[] args) {
        MainFileCopy mainFileCopy = new MainFileCopy();
        mainFileCopy.copyUpper();
    }

    private void input() {
        System.out.println("Введите имя файла ");
        Scanner sc = new Scanner(System.in);
        inputFile = sc.nextLine();
        System.out.println("Введите имя копии");
        outputFile =sc.nextLine();
        File file = new File(inputFile);
        if (! file.isFile() && file.exists()) {
            throw new RuntimeException("Файла не существует");
        }
    }

    private void copyUpper() {
        try(InputStream is = new FileInputStream("pushkin.txt"); OutputStream fos = new FileOutputStream("ildar.txt")) {
            byte[] buffer = new byte[1024];
            int i;
            while ((i = is.read(buffer)) > -1) {
                fos.write(new String(buffer,0,i).toUpperCase().getBytes(StandardCharsets.UTF_8));
            }
            fos.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}

package ru.itis.inf403;

import java.io.*;
import java.util.Stack;

public class FileHandler {
    public static void merge(String filename1, String filename2,String finalname) {
        try(InputStream is1 = new FileInputStream(filename1);InputStream is2 = new FileInputStream(filename2);OutputStream fos = new FileOutputStream(finalname,true)) {
            int i1;
            while ((i1 = is1.read()) > -1) {
                fos.write(i1);
            }
            while((i1 = is2.read()) > -1) {
                fos.write(i1);
            }
            fos.flush();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static int checkJSON(String filename) throws NullPointerException, FileNotFoundException, IllegalArgumentException, IOException {
        if (filename == null) {
            throw new NullPointerException("Пустое значение");
        }
        if (!new File(filename).exists()) {
            throw new FileNotFoundException("Файл не найден");
        }
        InputStream is = new FileInputStream(filename);
            int i;
            int cnt = 1;
            Stack<Character> stack = new Stack<>();
            i = is.read();
            if ((char) i != '[' && (char) i != '{') {
                System.out.println((char) i);
                return cnt;
            }
            if ((char) i == '{' || (char) i == '[') {
                stack.push((char) i);
            }
            while ((i = is.read()) > -1) {
                if (!isValidJsonChar((char) i)) {
                    throw new IllegalArgumentException("Не соответствует алфавиту");
                }
                if (i == '{' || i == '[') {
                    stack.push((char) i);
                }
                if (i == ']' && stack.peek() == '{') {
                    return cnt;
                }
                if (i == '}' && stack.peek() == '[') {
                    return cnt;
                }
                if (i == ']' && stack.peek() == '[') {
                    stack.pop();
                }
                if (i == '}' && stack.peek() == '{') {
                    stack.pop();
                }
                cnt++;
            }
            if (!stack.isEmpty()) {
                return cnt;
            }
            is.close();
        return 0;
    }

    private static boolean isValidJsonChar(char ch) {
        return (ch >= ' ' && ch <= '~')
                || (ch == '\n' || ch == '\r' || ch == '\t')
                || (ch >= 'А' && ch <= 'я');
    }
}

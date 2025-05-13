package ru.itis.inf403;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainByteStreamInput {
    public static void main(String[] args) {

        byte[] bytes = {2,3,1,53,12,};
        InputStream is = null;
        try {
            is =new ByteArrayInputStream(bytes);
            int  i;
            while((i = is.read()) != -1) {
                System.out.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try{
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
        }


        try (InputStream bis = new ByteArrayInputStream(bytes)) {  //закрывает поток после  того как закончился try
            int  i;
            while((i = bis.read()) != -1) {
                System.out.println(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

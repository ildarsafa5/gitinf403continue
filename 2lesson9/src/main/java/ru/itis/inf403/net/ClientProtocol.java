package ru.itis.inf403.net;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


/**
 * Общение в рамках договоренности о формате сообщений(протокол)
 * |длина(4 байта)|основная часть заявленной длины|
 */
public class ClientProtocol {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1",50000);

            //Поток для чтения данных от сервера
            DataInputStream is = new DataInputStream(socket.getInputStream());
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            //Отправляем сообщение на сервер
            String message = "Hello from Client";
            int size = message.getBytes().length;

            os.writeInt(size);
            os.write(message.getBytes());
            os.flush();

            //читаем послание от сервера
            size = is.readInt(); //размер сообщения
            byte[] buffer = new byte[size];  //готовим буфер нужного размера
            is.read(buffer);
            System.out.println(new String(buffer));

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

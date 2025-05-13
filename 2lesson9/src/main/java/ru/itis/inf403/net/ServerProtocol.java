package ru.itis.inf403.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Общение в рамках договоренности о формате сообщений(протокол)
 * |длина(4 байта)|основная часть|
 */
public class ServerProtocol {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(50000);
            //ожидаем подключения клиента
            Socket clientSocket = serverSocket.accept();
            //дождались клиента
            //поток для чтения данных от клиента
            DataInputStream is = new DataInputStream(clientSocket.getInputStream());
            //поток для передачи данных клиенту
            DataOutputStream os = new DataOutputStream(clientSocket.getOutputStream());


            //Читаем послание от клиента
            int size = is.readInt(); //размер сообщения
            byte[] buffer = new byte[size];  //готовим буфер нужного размера
            is.read(buffer);
            System.out.println(new String(buffer));
            //Отправляем клиенту
            String message = "Hello from Server";
            size = message.getBytes().length;

            os.writeInt(size);
            os.write(message.getBytes());
            os.flush();

            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package ru.itis.inf403.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(50000);
            //ожидаем подключения клиента
            Socket clientSocket = serverSocket.accept();
            //дождались клиента
            //поток для чтения данных от клиента
            InputStream is = clientSocket.getInputStream();
            //поток для передачи данных клиенту
            OutputStream os = clientSocket.getOutputStream();

            byte[] buffer = new byte[100];
            //Читаем послание от клиента
            int i = is.read(buffer);
            System.out.println(new String(buffer,0,i));
            //Отправляем клиенту
            os.write("Hello from Server".getBytes());
            os.flush();

            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package ru.itis.inf403.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMessenger {
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

            while(true) {
                //Читаем послание от клиента
                int size = is.readInt(); //размер сообщения
                byte[] buffer = new byte[size];  //готовим буфер нужного размера
                is.read(buffer);
                String message = new String(buffer);
                System.out.println(message);
                if (message.equals("exit")) {
                    break;
                }
                Scanner scanner = new Scanner(System.in);
                message = scanner.nextLine();
                size = message.getBytes().length;

                os.writeInt(size);
                os.write(message.getBytes());
                os.flush();
                if (message.equals("exit")) {
                    break;
                }
            }
            clientSocket.close();
            serverSocket.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

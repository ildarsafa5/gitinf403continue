package ru.itis.inf403.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerStringProtocol {
    private static java.io.BufferedReader BufferedReader;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(50000);
            //ожидаем подключения клиента
            Socket clientSocket = serverSocket.accept();
            //дождались клиента
            //поток для чтения данных от клиента
            BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //поток для передачи данных клиенту
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            while(true) {
                String message = is.readLine();
                System.out.println(message);
                if (message.equals("exit")) {
                    break;
                }
                Scanner scanner = new Scanner(System.in);
                message = scanner.nextLine();

                os.write(message + "\n");
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

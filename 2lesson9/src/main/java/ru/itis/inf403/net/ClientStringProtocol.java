package ru.itis.inf403.net;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientStringProtocol {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1",50000);

            //Поток для чтения данных от сервера
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Поток для передачи данных серверу
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            while(true) {
                Scanner scanner = new Scanner(System.in);

                //Отправляем сообщение на сервер
                String message = scanner.nextLine();
                os.write(message+"\n");
                os.flush();
                if (message.equals("exit")) {
                    break;
                }

                //читаем послание от сервера
                message = is.readLine();
                System.out.println(message);
                if (message.equals("exit")) {
                    break;
                }
            }
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package ru.itis.inf403.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientMessenger {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1",50000);

            //Поток для чтения данных от сервера
            DataInputStream is = new DataInputStream(socket.getInputStream());
            DataOutputStream os = new DataOutputStream(socket.getOutputStream());
            while(true) {
                Scanner scanner = new Scanner(System.in);

                //Отправляем сообщение на сервер
                String message = scanner.nextLine();
                int size = message.getBytes().length;
                if (message.equals("exit")) {
                    break;
                }

                os.writeInt(size);
                os.write(message.getBytes());
                os.flush();

                //читаем послание от сервера
                size = is.readInt(); //размер сообщения
                byte[] buffer = new byte[size];  //готовим буфер нужного размера
                is.read(buffer);
                message = new String(buffer);
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

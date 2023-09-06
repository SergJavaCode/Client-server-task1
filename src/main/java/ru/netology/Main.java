package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("старт сервера");
        int port = 8090;
        try (ServerSocket serverSocket = new ServerSocket(port);) {// порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
            while (true) {
                try (Socket clientSocket = serverSocket.accept(); // ждем подключения
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {

                    System.out.println("New connection accepted");
                    out.println("Назовите своё имя:");
                    String entry = in.readLine();
                    final String name = entry;
                    System.out.println("Сервер принял имя: " + name);
                    out.println("Ведите свой возраст:");
                    int age = Integer.parseInt(in.readLine());
                    System.out.println("Сервер принял возраст: " + age);
                    out.println(String.format("Привет %s, твой возраст %d", name, age));
                    if (age >= 18) {
                        out.println("Вы можете претендовать на работу в нашей организации!");
                        out.println("Вы готовы приступить к работе? (yes/no)");
                        String result = in.readLine();
                        if (result.equalsIgnoreCase("yes")) {
                            out.println("Вы приняты на работу!");
                        } else {
                            out.println("Подумайте еще. Мы свяжемся с вами позже");
                        }

                    } else {
                        out.println("Вы можете пройти обучение в нашей организации!");
                        out.println("Вы готовы приступить к обучению? (yes/no)");
                        String result = in.readLine();
                        if (result.equalsIgnoreCase("yes")) {
                            out.println("Вы зачислены на обучение!");
                        } else {
                            out.println("Подумайте еще. Мы свяжемся с вами позже");
                        }

                    }

                    // выключаем соединения
                    System.out.println("Client disconnected");
                    System.out.println("Closing connections & channels.");
                    // закрываем сначала каналы сокета !
                    in.close();
                    out.close();
                    // потом закрываем сам сокет общения на стороне сервера!
                    serverSocket.close();
                    // потом закрываем сокет сервера который создаёт сокеты общения
                    // хотя при многопоточном применении его закрывать не нужно
                    // для возможности поставить этот серверный сокет обратно в ожидание нового подключения
                    System.out.println("Closing connections & channels - DONE.");
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
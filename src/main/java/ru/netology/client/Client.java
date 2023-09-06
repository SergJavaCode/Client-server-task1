package ru.netology.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "netology.homework";
        int port = 8090;
        try (Socket clientSocket = new Socket(host, port);
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String serverWord = in.readLine();
            System.out.println(serverWord);
            String message = br.readLine();// получаем ввод
            out.println(message);//отправляем на сервер
            serverWord = in.readLine(); // ждём, что скажет сервер
            System.out.println(serverWord); // получив - выводим на экран
            message = br.readLine();// получаем ввод
            out.println(message);//отправляем на сервер
            serverWord = in.readLine(); // ждём, что скажет сервер
            System.out.println(serverWord); // получив - выводим на экран
            serverWord = in.readLine(); // ждём, что скажет сервер
            System.out.println(serverWord); // получив - выводим на экран
            serverWord = in.readLine(); // ждём, что скажет сервер
            System.out.println(serverWord); // получив - выводим на экран
            message = br.readLine();    // получаем ввод
            out.println(message);       //отправляем на сервер
            serverWord = in.readLine(); // ждём, что скажет сервер
            System.out.println(serverWord); // получив - выводим на экран
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

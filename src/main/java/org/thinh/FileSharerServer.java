package org.thinh;

import java.io.*;
import java.net.*;

public class FileSharerServer {
    protected Socket clientSocket = null;
    protected ServerSocket serverSocket = null;
    protected FileSharerThread threads = null;
    protected File serverRoot = new File("D:\\UOIT\\Winter2021\\CSCI_2020U\\assignment_2\\testing\\server");

    public static int SERVER_PORT = 6868;
    public static int MAX_CLIENTS = 25;

    public FileSharerServer() {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("---------------------------------------------");
            System.out.println("File Sharer Server Application is running");
            System.out.println("---------------------------------------------");
            System.out.println("Listening to port: " + SERVER_PORT);
            while (true) {
                System.out.println("---------------------------------------------");
                clientSocket = serverSocket.accept();
                System.out.println("Connected with " + clientSocket.getLocalAddress());
                threads = new FileSharerThread(clientSocket, serverRoot);
                threads.start();
                System.out.println("Disconnected with " + clientSocket.getLocalAddress());
            }
        } catch (IOException e) {
            System.err.println("IOException while creating server connection");
        }
    }

    public static void main(String[] args) {
        FileSharerServer server = new FileSharerServer();
    }
}

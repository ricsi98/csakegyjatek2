package server;

import communication.Connection;
import controllers.GamePoolController;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int port = 4444;
    private ServerSocket ss;

    public void runServer() throws IOException {
        ss = new ServerSocket(port);
        System.out.println("Starting to listen");
        MessageHandler messageHandler = new GamePoolController();

        while(true) {
            Socket socket = ss.accept();
            System.out.println("New connection");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            System.out.println("Streams ready");

            ClientHandler ch = new ClientHandler(new Connection(socket, is, os), messageHandler);
            System.out.println("Starting handler");
            ch.start();
        }

    }


    public static void main(String[] args) {
        Server s = new Server();
        try {
            s.runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

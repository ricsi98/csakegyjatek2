package server;

import communication.Connection;
import communication.Message;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler extends Thread {

    private static List<Connection> connections;

    private Connection connection;
    private MessageHandler messageHandler;

    public ClientHandler(Connection connection, MessageHandler messageHandler) {
        this.connection = connection;
        this.messageHandler = messageHandler;
        if (connections == null) {
            connections = new ArrayList<Connection>();
        }
        connections.add(connection);
    }

    public void disposeConnection() {
        try {
            this.connection.close();
            connections.remove(this.connection);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcastMessage(Message message) {
        try {
            for (Connection c : connections) {
                c.getOos().writeObject(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void directMessage(Message message) {
        try {
            this.connection.getOos().writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while (this.connection.isOpen()) {
            try {
                Message m = (Message) this.connection.getOis().readObject();
                ClientContext context = new ClientContext(this.connection, this);
                this.messageHandler.process(context, m);
            } catch (EOFException e) {
                System.out.println("Client disconnected unexpectedly!");
                this.disposeConnection();
            } catch (IOException e) {
                e.printStackTrace();
                this.disposeConnection();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                this.disposeConnection();
            }
        }
        System.out.println("Closing handler");
    }
}

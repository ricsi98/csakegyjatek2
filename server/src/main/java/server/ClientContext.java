package server;

import communication.Connection;

public class ClientContext {

    private final Connection connection;
    private final ClientHandler handler;

    public ClientContext(Connection connection, ClientHandler handler) {
        this.connection = connection;
        this.handler = handler;
    }

    public ClientHandler getHandler() {
        return handler;
    }

    public Connection getConnection() {
        return connection;
    }
}

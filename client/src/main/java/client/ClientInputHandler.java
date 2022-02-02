package client;

import communication.Message;
import communication.MessageType;
import communication.messages.StringMessageData;
import communication.messages.meta.GameListResponse;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ClientInputHandler extends Thread {

    private ObjectInputStream ois;

    public ClientInputHandler(ObjectInputStream ois) {
        this.ois = ois;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message resp = (Message)ois.readObject();

                if (resp.getType() == MessageType.PING) {
                    System.out.println("PING");
                } else if (resp.getType() == MessageType.GAME_LIST_RESPONSE) {
                    GameListResponse gameList = (GameListResponse) resp.getData();
                    System.out.println("GAMES:");
                    for (String game : gameList.getGames()) {
                        System.out.println(game);
                    }
                } else if (resp.getType() == MessageType.OK) {
                    System.out.println("OK");
                } else if (resp.getType() == MessageType.ERROR) {
                    String error = ((StringMessageData) resp.getData()).getPayload();
                    System.out.println("ERROR: " + error);
                } else if (resp.getType() == MessageType.CHAT) {
                    String msg = ((StringMessageData) resp.getData()).getPayload();
                    System.out.println("CHAT: " + msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

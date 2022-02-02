package client;

import communication.Message;
import communication.MessageType;
import communication.messages.StringMessageData;
import communication.messages.meta.CreateGameRequest;
import communication.messages.meta.GameListResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getByName("localhost");
            Socket s = new Socket(ip, 4444);
            System.out.println("Connected");
            long id = ProcessHandle.current().pid();

            ObjectOutputStream ous = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            Scanner sc = new Scanner(System.in);

            while (true) {
                String command = sc.nextLine();
                Message req = null;
                if (command.startsWith("list")) {
                    req = new Message(MessageType.GAME_LIST_REQUEST, null);
                } else if (command.startsWith("ping")) {
                    req = Message.pingMessage();
                } else if (command.startsWith("create")) {
                    String name = Arrays.stream(command.split(" ")).toList().get(1);
                    int n = Integer.parseInt(Arrays.stream(command.split(" ")).toList().get(2));
                    req = new Message(MessageType.CREATE_GAME_REQUEST, new CreateGameRequest(name, n));
                }
                ous.writeObject(req);
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
                }
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

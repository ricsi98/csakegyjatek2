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

            ClientInputHandler oh = new ClientInputHandler(ois);
            oh.start();
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
                } else if (command.startsWith("tell")) {
                    String message = command.substring(5);
                    req = new Message(MessageType.CHAT, new StringMessageData(message));
                }
                ous.writeObject(req);

            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

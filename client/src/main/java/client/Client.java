package client;

import communication.Message;
import communication.MessageType;
import communication.StringMessageData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getByName("localhost");
            Socket s = new Socket(ip, 4444);
            System.out.println("Connected");
            long id = ProcessHandle.current().pid();


            ObjectOutputStream ous = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

            while (true) {
                Thread.sleep(3000);
                ous.writeObject(new Message(MessageType.STRING, new StringMessageData("Hello " + id)));
                Message resp = (Message)ois.readObject();
                if (resp.getType() == MessageType.STRING) {
                    String text = ((StringMessageData)resp.getData()).getPayload();
                    System.out.println(text);
                }
            }




        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

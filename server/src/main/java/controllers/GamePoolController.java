package controllers;

import communication.Message;
import communication.MessageType;
import communication.StringMessageData;
import server.ClientContext;
import server.MessageHandler;

public class GamePoolController implements MessageHandler {

    @Override
    public void process(ClientContext context, Message message) {
        System.out.println("Message");
        if (message.getType() == MessageType.PING) {
            context.getHandler().directMessage(Message.pingMessage());
        } else if (message.getType() == MessageType.STRING) {
            StringMessageData data = (StringMessageData) message.getData();
            context.getHandler().broadcastMessage(new Message(MessageType.STRING, new StringMessageData(data.getPayload())));
        }
    }
}

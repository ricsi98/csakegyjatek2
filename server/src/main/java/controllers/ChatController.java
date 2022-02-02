package controllers;

import communication.Message;
import communication.MessageType;
import communication.messages.StringMessageData;
import server.ClientContext;
import server.MessageHandler;

public class ChatController implements MessageHandler {
    @Override
    public void process(ClientContext context, Message message) {
        String msg = ((StringMessageData) message.getData()).getPayload();
        context.getHandler().broadcastMessage(
                new Message(MessageType.CHAT, new StringMessageData(msg)));
    }
}

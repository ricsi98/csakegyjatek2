package controllers;

import communication.Message;
import server.ClientContext;
import server.MessageHandler;

public class PingController implements MessageHandler {
    @Override
    public void process(ClientContext context, Message message) {
        context.getHandler().directMessage(Message.pingMessage());
    }
}

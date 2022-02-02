package server;

import communication.Message;

public interface MessageHandler {

    void process(ClientContext context, Message message);

}

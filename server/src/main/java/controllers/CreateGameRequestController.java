package controllers;

import communication.Message;
import communication.messages.meta.CreateGameRequest;
import model.Game;
import server.ClientContext;
import server.MessageHandler;

public class CreateGameRequestController implements MessageHandler {

    GamePoolController root;

    public CreateGameRequestController(GamePoolController root) {
        this.root = root;
    }

    @Override
    public void process(ClientContext context, Message message) {
        CreateGameRequest request = (CreateGameRequest) message.getData();
        root.getGames().add(new Game(request.getName(), request.getnPlayers()));
        context.getHandler().directMessage(Message.okMessage());
    }
}

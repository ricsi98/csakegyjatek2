package controllers;

import communication.Message;
import communication.MessageType;
import communication.messages.meta.GameListResponse;
import model.Game;
import server.ClientContext;
import server.MessageHandler;

public class GameListRequestController implements MessageHandler {
    GamePoolController root;

    public GameListRequestController(GamePoolController root) {
        this.root = root;
    }

    @Override
    public void process(ClientContext context, Message message) {
        GameListResponse response = new GameListResponse();
        for (Game g : this.root.getGames()) {
            response.getGames().add(g.getName());
        }
        context.getHandler().directMessage(new Message(MessageType.GAME_LIST_RESPONSE, response));
    }
}

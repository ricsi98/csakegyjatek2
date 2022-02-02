package controllers;

import communication.Message;
import communication.MessageType;
import communication.messages.StringMessageData;
import model.Game;
import model.Model;
import model.Player;
import server.ClientContext;
import server.MessageHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePoolController implements MessageHandler {

    private List<Game> games;
    private List<Player> players;

    private Map<MessageType, MessageHandler> handlers;

    public GamePoolController() {
        this.games = new ArrayList<>();
        this.players = new ArrayList<>();
        this.handlers = new HashMap<>();
        this.handlers.put(MessageType.PING, new PingController());
        this.handlers.put(MessageType.GAME_LIST_REQUEST, new GameListRequestController(this));
        this.handlers.put(MessageType.CREATE_GAME_REQUEST, new CreateGameRequestController(this));
    }

    public List<Game> getGames() {
        return this.games;
    }

    public void joinGame(ClientContext context, int gameId) {

    }

    @Override
    public void process(ClientContext context, Message message) {
        System.out.println("Message");
        if (!handlers.containsKey(message.getType())) {
            Message response = Message.errorMessage("Not supported message type");
            context.getHandler().directMessage(response);
        } else {
            handlers.get(message.getType()).process(context, message);
        }
    }
}

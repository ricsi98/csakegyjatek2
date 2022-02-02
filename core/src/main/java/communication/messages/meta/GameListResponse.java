package communication.messages.meta;

import communication.MessageData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameListResponse extends MessageData implements Serializable {

    private List<String> games;

    public GameListResponse() {
        this.games = new ArrayList<>();
    }

    public List<String> getGames() {
        return games;
    }

}

package communication.messages.meta;

import communication.MessageData;

import java.io.Serializable;

public class CreateGameRequest extends MessageData implements Serializable {

    private String name;
    private int nPlayers;

    public CreateGameRequest(String name, int n) {
        this.name = name;
        this.nPlayers = n;
    }

    public String getName() {
        return name;
    }

    public int getnPlayers() {
        return this.nPlayers;
    }
}

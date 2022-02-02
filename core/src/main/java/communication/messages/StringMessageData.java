package communication.messages;

import communication.MessageData;

import java.io.Serializable;

public class StringMessageData extends MessageData implements Serializable {

    private String payload;

    public StringMessageData(String payload) {
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }
}

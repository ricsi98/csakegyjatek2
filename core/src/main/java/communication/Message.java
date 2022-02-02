package communication;

import java.io.Serializable;

public class Message implements Serializable {

    public final MessageType type;
    public final MessageData data;

    public Message(MessageType type, MessageData data) {
        this.type = type;
        this.data = data;
    }

    public MessageData getData() {
        return this.data;
    }

    public MessageType getType() {
        return type;
    }

    public static Message pingMessage() {
        return new Message(MessageType.PING, null);
    }

    public static Message errorMessage(String errorText) {
        return new Message(MessageType.ERROR, new StringMessageData(errorText));
    }

}

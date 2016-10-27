package Entity;

import java.io.Serializable;

public class Message implements Serializable {

    public enum MessageType {

        /**
         * The HELLO is the first message received at the listening party of a
         * dialog.
         */
        HELLO,
        
        /**
         * The BYE message is used for notifying other clients to remove their respective
         * User object
         */
        BYE,
        /**
         * Update message for files
         */
        UPDATE,
        /**
         * USERNAME is the response to the initial HELLO message. It is received
         * at the initiating party of a dialog.
         */
        USERNAME;
    }
    private MessageType messageType;
    private Object[] payload;

    public Message(MessageType messageType, Object... payload) {
        this.messageType = messageType;
        this.payload = payload;
    }

    public boolean isValid(int payloadSize) {
        if (payload.length != payloadSize) {
            return false;
        }

        return true;
    }
    
    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Object[] getPayload() {
        return payload;
    }

    public void setPayload(Object[] payload) {
        this.payload = payload;
    }
}

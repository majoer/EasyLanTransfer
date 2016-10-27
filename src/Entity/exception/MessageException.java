package Entity.exception;

import java.io.IOException;

public class MessageException extends IOException {
    public MessageException() {
        super("Invalid message format");
    }
}

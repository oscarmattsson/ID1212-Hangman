package oscarmat.kth.id1212.hangman.common;

import java.io.Serializable;

/**
 * Holds data for transfer between server and client
 * (or vice versa).
 */
public class Message<T> implements Serializable {

    private MessageType type;
    private final T value;

    /**
     * Create a new primitive message.
     * @param type Type of message being sent.
     */
    public Message(MessageType type) {
        this.type = type;
        this.value = null;
    }

    /**
     * Create a new message.
     * @param type Type of message being sent.
     * @param value Value of the message.
     */
    public Message(MessageType type, T value) {
        this.type = type;
        this.value = value;
    }

    /**
     * @return Type of the message.
     */
    public MessageType getType() {
        return type;
    }

    /**
     * @return Value of the message.
     */
    public T getValue() {
        return value;
    }
}

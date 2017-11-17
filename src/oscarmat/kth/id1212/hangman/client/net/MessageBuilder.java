package oscarmat.kth.id1212.hangman.client.net;

import oscarmat.kth.id1212.hangman.common.Message;
import oscarmat.kth.id1212.hangman.common.MessageType;

/**
 * Builds messages for transfer to server.
 */
class MessageBuilder {
    Message<String> test() {
        return new Message(MessageType.LETTER, "Hi!");
    }
}

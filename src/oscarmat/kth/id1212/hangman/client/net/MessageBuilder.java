package oscarmat.kth.id1212.hangman.client.net;

import oscarmat.kth.id1212.hangman.common.Message;
import oscarmat.kth.id1212.hangman.common.MessageType;

/**
 * Builds messages for transfer to server.
 */
class MessageBuilder {

    /**
     * @return Message to start a new game.
     */
    Message newGame() {
        MessageType type = MessageType.NEWGAME;
        return new Message(type);
    }

    /**
     * @return Message to exit the game session.
     */
    Message exit() {
        MessageType type = MessageType.EXIT;
        return new Message(type);
    }

    /**
     * @return The player's current score.
     */
    Message getScore() {
        MessageType type = MessageType.SCORE;
        return new Message(type);
    }

    /**
     * @param letter Letter guessed by the user.
     * @return Message containing the guessed letter.
     */
    Message<Character> guessLetter(char letter) {
        MessageType type = MessageType.LETTER;
        return new Message<>(type, letter);
    }

    /**
     * @param word Word guessed by the user.
     * @return Message containing the guessed word.
     */
    Message<String> guessWord(String word) {
        MessageType type = MessageType.WORD;
        return new Message<>(type, word);
    }
}

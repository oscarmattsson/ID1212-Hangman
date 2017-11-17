package oscarmat.kth.id1212.hangman.server.net;

import oscarmat.kth.id1212.hangman.common.Message;
import oscarmat.kth.id1212.hangman.common.MessageType;
import oscarmat.kth.id1212.hangman.server.model.GameNotOverException;
import oscarmat.kth.id1212.hangman.server.model.GameState;

/**
 * Builds messages for transfer to client.
 */
class MessageBuilder {

    Message<Message[]> gameState(GameState gameState) {
        MessageType type = MessageType.GAMESTATE;
        Message[] children = {
                new Message<String>(MessageType.WORDSTATE, gameState.getWordState()),
                new Message<Integer>(MessageType.FAILEDATTEMPTS, gameState.getFailedAttempts()),
                new Message<Integer>(MessageType.MAXATTEMPTS, gameState.getMaximumAllowedAttempts())
        };

        return new Message<>(type, children);
    }

    /**
     * @param gameState State of the game after its end.
     * @return Message containing the end state of the game.
     * @throws GameNotOverException If called before game has ended.
     */
    Message<Message[]> gameOver(GameState gameState) throws GameNotOverException {
        MessageType type = MessageType.GAMEOVER;
        Message[] children = {
                new Message<Boolean>(MessageType.WON, gameState.isGameWon()),
                gameState(gameState)
        };

        return new Message<>(type, children);
    }

}

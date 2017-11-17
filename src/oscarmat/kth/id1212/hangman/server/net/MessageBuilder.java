package oscarmat.kth.id1212.hangman.server.net;

import oscarmat.kth.id1212.hangman.server.model.GameNotOverException;
import oscarmat.kth.id1212.hangman.server.model.GameState;

/**
 * Parses output data from the server to build a message for the client,
 * following the protocol as described in the ClientHandler class.
 */
class MessageBuilder {

    private static final String GAMESTATE = "gamestate";
    private static final String WORDSTATE = "wordstate";
    private static final String FAILEDATTEMPTS = "failedattempts";
    private static final String MAXATTEMPTS = "maxattempts";
    private static final String GAMEOVER = "gameover";
    private static final String WON = "won";
    private static final String LOST = "lost";
    private static final String MSG_DELIMETER = ":";

    /**
     * Returns a message with the state of the game.
     * @param gameState The current state of the game.
     * @return gamestate:[wordstate:<wordstate>,failedattempts:<failedattempts>,maxattempts:<maxattempts>]
     */
    String gameState(GameState gameState) {
        StringBuilder msg = new StringBuilder();
        msg.append(GAMESTATE);
        msg.append("[");
        msg.append(WORDSTATE).append(MSG_DELIMETER).append(gameState.getWordState()).append(",");
        msg.append(FAILEDATTEMPTS).append(":").append(gameState.getFailedAttempts()).append(",");
        msg.append(MAXATTEMPTS).append(":").append(gameState.getMaximumAllowedAttempts());
        msg.append("]");
        return msg.toString();
    }

    /**
     * Returns a message with the result of the game.
     * @param gameState The current state of the game.
     * @return gameover:won or gameover:lost
     */
    String gameOver(GameState gameState) throws GameNotOverException {
        StringBuilder msg = new StringBuilder();
        if(gameState.isGameWon()) {
            msg.append(GAMEOVER).append(":").append(WON);
        }
        else if(gameState.isGameLost()) {
            msg.append(GAMEOVER).append(":").append(LOST);
        }
        return msg.toString();
    }
}

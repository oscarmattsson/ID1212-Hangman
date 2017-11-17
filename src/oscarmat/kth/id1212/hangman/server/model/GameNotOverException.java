package oscarmat.kth.id1212.hangman.server.model;

/**
 * Is thrown when the result of a game is requested
 * before the game is over.
 */
public class GameNotOverException extends RuntimeException {

    GameNotOverException() {
        super("Game is not over, cannot get the result of the game yet.");
    }

}


package oscarmat.kth.id1212.hangman.common;

import oscarmat.kth.id1212.hangman.server.model.Game;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the state of a game instance.
 * @author oscar
 */
public class GameDTO implements Serializable {

    private final int failedAttempts;
    private final int maxAttempts;
    private final String wordState;
    private final boolean isGameOver;
    private final boolean isGameWon;
    private final boolean isGameLost;
    private final Map<String, Boolean> guesses;

    private int score;

    public GameDTO(Game game, int score) {
        failedAttempts = game.getFailedAttempts();
        maxAttempts = game.getMaximumAllowedAttempts();
        wordState = game.getWordState();
        isGameOver = game.isGameOver();
        isGameWon = game.isGameWon();
        isGameLost = game.isGameLost();
        guesses = game.getGuesses();

        this.score = score;
    }
    /**
     * @return Get current amount of failed attempts.
     */
    public int getFailedAttempts() {
        return failedAttempts;
    }
    
    /**
     * @return Get maximum amount of allowed attempts.
     */
    public int getMaximumAllowedAttempts() {
        return maxAttempts;
    }
    
    /**
     * @return Current state of guessed word.
     */
    public String getWordState() {
        return wordState;
    }
    
    /**
     * @return true if the game has been won, false otherwise.
     */
    public boolean isGameWon() {
        return isGameWon;
    }
    
    /**
     * @return true if the game has been lost, false otherwise. 
     */
    public boolean isGameLost() {
        return isGameLost;
    }
    
    /**
     * @return true if the game is either won or lost, false otherwise.
     */
    public boolean isGameOver() {
        return isGameOver;
    }

    /**
     * @return Guesses so far and their results.
     */
    public Map<String, Boolean> getGuesses() {
        return guesses;
    }

    /**
     * @return Total score so far.
     */
    public int getScore() {
        return score;
    }
}

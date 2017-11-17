
package oscarmat.kth.id1212.hangman.server.model;

/**
 * Represents the state of a game instance.
 * @author oscar
 */
public interface GameState {
    
    /**
     * @return Get current amount of failed attempts.
     */
    public int getFailedAttempts();
    
    /**
     * @return Get maximum amount of allowed attempts.
     */
    public int getMaximumAllowedAttempts();
    
    /**
     * @return Current state of guessed word.
     */
    public String getWordState();
    
    /**
     * @return true if the game has been won, false otherwise.
     */
    public boolean isGameWon() throws GameNotOverException;
    
    /**
     * @return true if the game has been lost, false otherwise. 
     */
    public boolean isGameLost() throws GameNotOverException;
    
    /**
     * @return true if the game is either won or lost, false otherwise.
     */
    public boolean isGameOver();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public boolean isGameWon();
    
    /**
     * @return true if the game has been lost, false otherwise. 
     */
    public boolean isGameLost();
    
    /**
     * @return true if the game is either won or lost, false otherwise.
     */
    public boolean isGameOver();
}

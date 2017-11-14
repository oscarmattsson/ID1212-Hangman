/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarmat.kth.id1212.hangman.server.model;

/**
 * Represents an instance of a hangman game.
 * Controls the remaining amount of attempts
 * and the word selected for the game.
 * @author oscar
 */
public class Game implements GameState {
    
    private GameWord word;
    private int attempts;
    
    /**
     * Create a new game instance with a random word.
     * @param wordList List of potential words.
     */
    public Game(String[] wordList) {
        attempts = 1;
        word = new GameWord(wordList);
    }
    
    /**
     * @return Get maximum amount of allowed attempts.
     */
    @Override
    public int getMaximumAllowedAttempts() {
        return word.getLength();
    }
    
    /**
     * @return Get current amount of failed attempts.
     */
    @Override
    public int getFailedAttempts() {
        return attempts;
    }
    
    /**
     * @return Current state of guessed word.
     */
    @Override
    public String getWordState() {
        return word.getClientWord();
    }

    /**
     * @return true if the game is either won or lost, false otherwise.
     */
    @Override
    public boolean isGameLost() {
        return getFailedAttempts() == getMaximumAllowedAttempts();
    }
    
    /**
     * @return true if the game has been won, false otherwise.
     */
    @Override
    public boolean isGameWon() {
        return word.getWord().equals(word.getClientWord());
    }
    
    /**
     * @return true if the game is either won or lost, false otherwise.
     */
    @Override
    public boolean isGameOver() {
        return isGameLost() || isGameWon();
    }
    
    /**
     * Check if submitted letter is correct and adjust
     * the game state accordingly.
     * @param guess Guessed letter.
     * @return true if letter is correct, false otherwise.
     */
    public GameState play(char guess) {
        boolean isCorrect = word.checkLetter(guess);
        if(!isCorrect) {
            attempts++;
        }
        return this;
    }
    
    /**
     * Check if submmitted word is correct and adjust
     * the game state accordingly.
     * @param guess Guessed word.
     * @return true if word is correct, false otherwise.
     */
    public GameState play(String guess) {
        boolean isCorrect = word.checkWord(guess);
        if(!isCorrect) {
            attempts++;
        }
        return this;
    }
    
}

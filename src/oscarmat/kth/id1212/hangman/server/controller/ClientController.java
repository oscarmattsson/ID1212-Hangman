
package oscarmat.kth.id1212.hangman.server.controller;

import oscarmat.kth.id1212.hangman.server.model.Game;
import oscarmat.kth.id1212.hangman.server.model.GameState;

/**
 * Controller for game instances for a single client.
 *
 * @author Oscar Mattsson
 */
public class ClientController {
    
    private Game game;
    private final String[] wordList;
    
    /**
     * Create a new controller instance for a single client with the
     * given word list. 
     * @param wordList Array of potential words used in the game.
     */
    public ClientController(String[] wordList) {
        this.wordList = wordList;
    }
    
    /**
     * Create a new game instance with a new random word from the
     * word list.
     */
    public void newGame() {
        game = new Game(wordList);
    }
    
    /**
     * Perform a guess in the game with a single letter.
     * @param guess The guessed letter.
     * @return The updated state of the game.
     */
    public GameState play(char guess) {
        return game.play(guess);
    }
    
    /**
     * Perform a guess in the game with a whole word.
     * @param guess The guessed word.
     * @return The updated state of the game.
     */
    public GameState play(String guess) {
        return game.play(guess);
    }
}

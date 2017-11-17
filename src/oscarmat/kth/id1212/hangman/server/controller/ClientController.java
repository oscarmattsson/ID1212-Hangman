
package oscarmat.kth.id1212.hangman.server.controller;

import oscarmat.kth.id1212.hangman.server.model.Game;
import oscarmat.kth.id1212.hangman.server.model.GameOverException;
import oscarmat.kth.id1212.hangman.server.model.GameState;

/**
 * Controller for game instances for a single client.
 *
 * @author Oscar Mattsson
 */
public class ClientController {
    
    private Game game;
    private int score;
    private final String[] wordList;
    
    /**
     * Create a new controller instance for a single client with the
     * given word list. 
     * @param wordList Array of potential words used in the game.
     */
    public ClientController(String[] wordList) {
        this.wordList = wordList;
        score = 0;
    }

    /**
     * @return The current state of the game.
     */
    public GameState getGameState() {
        return game;
    }

    /**
     * @return User score based on wins/losses.
     */
    public int getScore() {
        return score;
    }

    /**
     * Create a new game instance with a new random word from the
     * word list.
     * @return State of the new game.
     */
    public GameState newGame() {
        if(game != null && !game.isGameOver()) {
            score--;
        }
        return game = new Game(wordList);
    }
    
    /**
     * Perform a guess in the game with a single letter.
     * @param guess The guessed letter.
     * @return The updated state of the game.
     */
    public GameState play(char guess) throws GameOverException {
        if(game.isGameOver()) {
            if(game.isGameWon()) score++;
            else if(game.isGameLost()) score--;
        }
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


package oscarmat.kth.id1212.hangman.server.controller;

import oscarmat.kth.id1212.hangman.server.model.Game;
import oscarmat.kth.id1212.hangman.server.model.GameOverException;
import oscarmat.kth.id1212.hangman.common.GameDTO;

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
    public GameDTO getGameState() {
        return getDTO();
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
    public GameDTO newGame() {
        if(game != null && !game.isGameOver()) {
            score--;
        }

        game = new Game(wordList);
        return getDTO();
    }
    
    /**
     * Perform a guess in the game with a single letter.
     * @param guess The guessed letter.
     * @return The updated state of the game.
     * @throws GameOverException If trying to play after game is over.
     */
    public GameDTO play(char guess) throws GameOverException {
        game.play(guess);
        if(game.isGameOver()) updateScore();
        return getDTO();
    }

    /**
     * Perform a guess in the game with a whole word.
     * @param guess The guessed word.
     * @return The updated state of the game.
     * @throws GameOverException If trying to play after game is over.
     */
    public GameDTO play(String guess) throws GameOverException {
        game.play(guess);
        if(game.isGameOver()) updateScore();
        return getDTO();
    }

    private void updateScore() {
        if(game.isGameLost()) {
            score--;
        }
        else if(game.isGameWon()) {
            score++;
        }
    }

    private GameDTO getDTO() {
        return new GameDTO(game, score);
    }
}

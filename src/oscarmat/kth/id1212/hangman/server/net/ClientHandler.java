
package oscarmat.kth.id1212.hangman.server.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.net.Socket;
import oscarmat.kth.id1212.hangman.server.controller.ClientController;
import oscarmat.kth.id1212.hangman.server.model.GameState;

/**
 * Handles communication with a single client and game instances related
 * to that client. Communication with the ClientHandler follows the protocol
 * as described below. Keywords used by protocol are surrounded by square 
 * brackets.
 *   1. CLIENT sends desired [alias] for leaderboard followed by a new line.
 *      Only alphanumeric characters are allowed in the alias.
 *      ex. alias:oscarmat93\n
 *   2. SERVER responds with keyword [gamestate] followed by the state of
 *      the game as comma separated list of key-value pairs as described below
 *      and then ends with a new line.
 *     2.1 Keyword [wordstate] followed by state of the word,
 *         incomplete letter positions are represented by underscore ('_')
 *     2.2 Keyword [failedattempts] followed by the amount of failed attempts
 *         on a word performed so far.
 *     2.2 Keyword [maxattempts] followed by the maximum amount
 *         of allowed attempts on a word. 
 *     ex. gamestate:[wordstate:h_ngm_n,failedattempts:3,maxattempts:7]\n
 *   3a. CLIENT sends either a single [letter] or a [word] as a guess followed
 *      by a new line.
 *     3.1 ex. letter:a\n
 *     3.2 ex. word:hangman\n
 *   4a. SERVER replies either with [gameover] followed by the result (won/lost)
 *      followed by a new line and then the [gamestate] (see 2) followed by a 
 *      new line.
 *      ex1. gameover:won\n
 *           gamestate:[wordstate:hangman,failedattempts:5,maxattempts:7]\n
 *      ex2. gameover:lost\n
 *           gamestate:[wordstate:h_ngm_n,failedattempts:7,maxattempts:7]\n
 *   3b. CLIENT sends [newgame] followed by a new line to either forfeit the 
 *       current game or start a new one after a completed game.
 *       ex. newgame\n
 *   4b. SERVER returns the [gamestate] (see 2).
 *   3c. CLIENT sends [end] followed by a new line to end the connection to
 *       the server.
 *       ex. end\n
 * @author oscar
 */
class ClientHandler implements Runnable {

    private static final String ALIAS = "alias";
    private static final String LETTER = "letter";
    private static final String WORD = "word";
    private static final String NEWGAME = "newgame";
    private static final String END = "end";
    
    private final GameServer server;
    private final Socket clientSocket;
    
    private BufferedReader reciever;
    private PrintWriter sender;
    
    private final ClientController controller;
    private String name;
    private boolean running;
    
    /**
     * Initiate a handler for a single client with the given word list used
     * for potential words in game instances. 
     * @param server The parent server instance.
     * @param clientSocket The socket for communicating with the client.
     * @param wordList Array of potential words used in game instances.
     */
    ClientHandler(GameServer server, Socket clientSocket, String[] wordList) {
        this.server = server;
        this.clientSocket = clientSocket;
        
        controller = new ClientController(wordList);
        running = true;
    }
    
    /**
     * Start communicating with client following the protocol as described
     * in the class description.
     */
    @Override
    public void run() {
        try {
            reciever = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sender = new PrintWriter(clientSocket.getOutputStream(),false);

            while(running) {

            }
        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private class MessageParser {

        String message;

        MessageParser(String message) {
            this.message = message;
        }



    }
    
}

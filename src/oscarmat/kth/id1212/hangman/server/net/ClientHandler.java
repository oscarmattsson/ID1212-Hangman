
package oscarmat.kth.id1212.hangman.server.net;

import java.io.*;
import java.net.Socket;

import oscarmat.kth.id1212.hangman.common.Message;
import oscarmat.kth.id1212.hangman.server.controller.ClientController;

/**
 * Handles communication with a single client and game instances related
 * to that client.
 * @author oscar
 */
class ClientHandler implements Runnable {

    private final GameServer server;
    private final Socket clientSocket;

    private MessageBuilder builder;
    
    private final ClientController controller;
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
        builder = new MessageBuilder();
    }
    
    /**
     * Start communicating with client following the protocol as described
     * in the class description.
     */
    @Override
    public void run() {
        try {
            ObjectInputStream receiver = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream sender = new ObjectOutputStream(clientSocket.getOutputStream());

            controller.newGame();

            while(running) {
                Message message = (Message)receiver.readObject();
                sender.writeObject(builder.gameState(controller.getGameState()));
            }
        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
        catch(ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}


package oscarmat.kth.id1212.hangman.server.net;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

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

            String user = clientSocket.getInetAddress().toString();

            while(running) {
                Message message = (Message)receiver.readObject();
                switch (message.getType()) {
                    case NEWGAME:
                        sender.writeObject(controller.newGame());
                        System.out.println(user + ": Started new game.");
                        break;
                    case LETTER:
                        char letter = (char)message.getValue();
                        sender.writeObject(controller.play(letter));
                        System.out.println(user + ": Guessed the letter '" + letter + "'");
                        break;
                    case WORD:
                        String word = (String)message.getValue();
                        sender.writeObject(controller.play(word));
                        System.out.println(user + ": Guessed the word '" + word + "'");
                        break;
                    case SCORE:
                        int score = controller.getScore();
                        sender.writeInt(score);
                        sender.flush();
                        System.out.println(user + ": Requested score, returning " + score);
                        break;
                    case EXIT:
                        clientSocket.close();
                        System.out.println(user + ": Disconnected.");
                        this.running = false;
                        break;
                }
                sender.reset();
            }
        }
        catch(SocketException e) {
            System.out.println(clientSocket.getInetAddress() + ": Error, closing socket.");
        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
        catch(ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}

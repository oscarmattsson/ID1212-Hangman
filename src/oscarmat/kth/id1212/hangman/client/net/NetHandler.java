package oscarmat.kth.id1212.hangman.client.net;

import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import oscarmat.kth.id1212.hangman.common.GameState;
import oscarmat.kth.id1212.hangman.common.Message;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NetHandler {

    private static final int CONNECT_TIMEOUT = 10000;
    private static final int SO_TIMEOUT = 10000;

    private final Socket socket;
    private final ObjectOutputStream sender;
    private final ObjectInputStream receiver;
    private final MessageBuilder builder;

    public NetHandler(String host, int port, Window window) throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress(host, port), CONNECT_TIMEOUT);
        socket.setSoTimeout(SO_TIMEOUT);

        sender = new ObjectOutputStream(socket.getOutputStream());
        receiver = new ObjectInputStream(socket.getInputStream());
        builder = new MessageBuilder();

        window.setOnCloseRequest(this::onWindowClose);
    }

    private void onWindowClose(WindowEvent event) {
        NetHandler net = this;
        Task endTask = new Task() {
            @Override
            protected Object call() throws Exception {
                net.endSession();
                return null;
            }
        };
        Thread thread = new Thread(endTask);
        thread.start();
    }

    public GameState newGame() {
        send(builder.newGame());
        return receiveGameState();
    }

    public GameState guessLetter(char letter) {
        send(builder.guessLetter(letter));
        return receiveGameState();
    }

    public GameState guessWord(String word) {
        send(builder.guessWord(word));
        return receiveGameState();
    }

    public int getScore() {
        send(builder.getScore());
        return receiveInt();
    }

    public void endSession() {
        send(builder.exit());
    }

    /**
     * Send a message to the server.
     * @param sendMessage Message to be sent.
     */
    private void send(Message sendMessage) {
        try {
            sender.writeObject(sendMessage);
        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Wait for a message from the server.
     * @return Message containing new state of the game.
     */
    private GameState receiveGameState() {
        try {
            return (GameState)receiver.readObject();
        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
        catch(ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private int receiveInt() {
        try {
            return receiver.readInt();
        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

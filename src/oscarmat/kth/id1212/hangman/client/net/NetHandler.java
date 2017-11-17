package oscarmat.kth.id1212.hangman.client.net;

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

    public NetHandler(String host, int port) throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress(host, port), CONNECT_TIMEOUT);
        socket.setSoTimeout(SO_TIMEOUT);

        sender = new ObjectOutputStream(socket.getOutputStream());
        receiver = new ObjectInputStream(socket.getInputStream());
        builder = new MessageBuilder();
    }

    public Message newGame() {
        return send(builder.newGame());
    }

    public Message<Message[]> guessLetter(char letter) {
        return send(builder.play(letter));
    }

    public Message guessWord(String word) {
        return send(builder.play(word));
    }

    public Message endSession() {

    }

    private Message send(Message sendMessage) {
        try {
            sender.writeObject(sendMessage);
            return (Message)receiver.readObject();
        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
        catch(ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

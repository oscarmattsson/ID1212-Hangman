package oscarmat.kth.id1212.hangman.client.net;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NetHandler {

    private static final int CONNECT_TIMEOUT = 10000;
    private static final int SO_TIMEOUT = 10000;

    private Socket socket;
    private PrintWriter sender;
    private BufferedReader receiver;

    public NetHandler(String host, int port) throws IOException {
        socket = new Socket();
        socket.connect(new InetSocketAddress(host, port), CONNECT_TIMEOUT);
        socket.setSoTimeout(SO_TIMEOUT);

        sender = new PrintWriter(socket.getOutputStream(), true);
        receiver = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String send(String msg) {
        try {
            sender.println(msg);
            return receiver.readLine();
        }
        catch(IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

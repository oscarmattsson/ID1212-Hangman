/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarmat.kth.id1212.hangman.server.net;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Starts a server which picks up clients connecting
 * to the server sockets and handles them on a new thread.
 * @author oscar
 */
public class GameServer {

    private final String[] wordList;

    public GameServer(String[] wordList) {
        this.wordList = wordList;
    }

    /**
     * Start the server.
     * @param port Port to use for the server.
     */
    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server has started on port " + port + ".");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println(clientSocket.getInetAddress().getHostAddress() + " has connected.");
                startHandler(clientSocket);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Launch a handler for a client in a new thread.
     * @param clientSocket Socket of the client.
     */
    private void startHandler(Socket clientSocket) {
        ClientHandler handler = new ClientHandler(this, clientSocket, wordList);
        Thread thread = new Thread(handler);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }
}

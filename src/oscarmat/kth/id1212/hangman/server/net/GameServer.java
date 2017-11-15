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
import java.util.ArrayList;
import java.util.List;
import oscarmat.kth.id1212.hangman.server.controller.ServerController;

/**
 *
 * @author oscar
 */
public class GameServer {

    private final ServerController controller;
    private final String[] wordList;
    private final List<ClientHandler> clients;
    
    public GameServer(String[] wordList) {
        controller = new ServerController();
        this.wordList = wordList;
        clients = new ArrayList<>();
    }

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                startHandler(clientSocket);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private void startHandler(Socket clientSocket) {
        ClientHandler handler = new ClientHandler(this, clientSocket, wordList);
        clients.add(handler);
        Thread thread = new Thread(handler);
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }
    
    void updateLeaderboard(String name, int score) {
        
    }
}

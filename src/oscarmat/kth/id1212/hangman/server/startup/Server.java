/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarmat.kth.id1212.hangman.server.startup;

import oscarmat.kth.id1212.hangman.server.file.WordReader;
import oscarmat.kth.id1212.hangman.server.net.GameServer;

import java.io.IOException;
import java.util.MissingResourceException;

/**
 * Start up a hangman game server.
 * @author oscar
 */
public class Server {

    private static final int DEFAULT_PORT = 1337;
    
    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        String wordListUrl = "";

        try {
            if(args.length < 1) {
                throw new Exception("Missing word list parameter");
            }
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-w":
                        wordListUrl = args[++i];
                        break;
                    case "-p":
                        port = Integer.parseInt(args[++i]);
                        break;
                    default:
                        throw new IllegalArgumentException("No parameter matching the given argument.");
                }
            }
        }
        catch (Exception e) {
            System.out.println("Invalid arguments, usage: -w word-list [-p port]");
            System.exit(1);
        }

        try {
            final String[] wordList = WordReader.getWords(wordListUrl);
            GameServer server = new GameServer(wordList);
            server.start(port);;
        }
        catch (IOException e) {
            System.out.println("Error loading word file, exiting program.");
            System.exit(2);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarmat.kth.id1212.hangman.server.startup;

import oscarmat.kth.id1212.hangman.server.file.WordReader;
import oscarmat.kth.id1212.hangman.server.net.GameServer;

/**
 * Start up a hangman game server.
 * @author oscar
 */
public class Startup {

    private static final int DEFAULT_PORT = 1337;
    
    public static void main(String[] args) {
        final String[] wordList = WordReader.getWords("Assets/words.txt");
        GameServer server = new GameServer(wordList);
        server.start(DEFAULT_PORT);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarmat.kth.id1212.hangman.server.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author oscar
 */
public class WordReader {
    
    /**
     * Hide constructor, not meant to be instantiated. 
     */
    private WordReader() {}
    
    /**
     * Fetch all words, separated by line breaks,
     * from a file and put them in an array.
     * @param filePath Path to the words file
     * @return An array containing all words in the file.
     */
    public static String[] getWords(String filePath) throws IOException {
        List<String> wordList = new ArrayList<>();
        BufferedReader in;

        in = new BufferedReader(new FileReader(filePath));
        String line;
        while((line = in.readLine()) != null) {
            wordList.add(line);
        }

        return wordList.toArray(new String[0]);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarmat.kth.id1212.hangman.server.model;

import java.io.Serializable;
import java.util.Random;

/**
 * Represents the actual word to be guessed and the current state
 * of the word guessed by the client, as well as methods to handle
 * guesses of characters and entire words.
 * 
 * @author oscar
 */
public class GameWord implements Serializable {
    
    private final String word;
    private String clientWord = "";
    
    /**
     * Generates a random word from an array of words as the word to be guessed
     * and sets the current state of the word guessed by the client to
     * a number of underscores ('_') corresponding to the selected word length.
     * @param wordList Array of potential words
     */
    GameWord(String[] wordList) {
        Random r = new Random();
        word = wordList[r.nextInt(wordList.length)].toUpperCase();
        for(int i = 0; i < word.length(); i++) {
            clientWord += "_";
        }
    }
    
    /**
     * @return Length of the selected word
     */
    int getLength() {
        return word.length();
    }
    
    /**
     * @return Complete word for the game.
     */
    String getWord() {
        return word;
    }
    
    /**
     * @return Partial word in the state based on guesses by client.
     */
    String getClientWord() {
        return clientWord;
    }
    
    /**
     * Checks if the submitted letter matches a letter in the word 
     * and replaces an underscore in the client word if the letter is
     * a match. Does nothing if the letter already exists in the current
     * state of the client word. 
     * @param guess Guessed letter to be searched for in word.
     * @return true if the letter exists in the word, otherwise false.
     */
    boolean checkLetter(char guess) {
        guess = Character.toUpperCase(guess);
        if(clientWord.indexOf(guess) != -1) {
            if(word.indexOf(guess) != -1) {
                addCharacter(guess);
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return true;
        }
    }
    
    /**
     * Checks if the submitted word matches the word selected for the game.
     * If the word is a match, the client word is updated to match the 
     * complete word.
     * @param guess Guessed word to be compared to game word. 
     * @return true if the guessed word is correct, otherwise false.
     */
    boolean checkWord(String guess) {
        guess = guess.toUpperCase();
        if(word.equals(guess)) {
            clientWord = word;
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Adds a matching character for the word to all corresponding locations
     * in the client word.
     * @param c The character to add.
     */
    private void addCharacter(char c) {
        String tempWord = word;
        StringBuilder newClientWord = new StringBuilder(clientWord);
        int i;
        while((i = tempWord.indexOf(c)) != -1) {
            newClientWord.setCharAt(i, c);
            tempWord = tempWord.substring(i+1);
        }
        clientWord = newClientWord.toString();
    }

    
}

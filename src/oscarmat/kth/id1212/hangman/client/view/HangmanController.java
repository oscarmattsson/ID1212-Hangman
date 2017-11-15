/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarmat.kth.id1212.hangman.client.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author oscar
 */
public class HangmanController implements Initializable {
    
    @FXML
    private Label word;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        //word.setText(words[random.nextInt(words.length)]);
    }
    
    /**
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    
}

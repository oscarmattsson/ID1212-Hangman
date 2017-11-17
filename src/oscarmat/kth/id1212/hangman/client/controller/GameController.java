/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarmat.kth.id1212.hangman.client.controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import oscarmat.kth.id1212.hangman.client.net.NetHandler;
import oscarmat.kth.id1212.hangman.client.view.components.HeartComponent;
import oscarmat.kth.id1212.hangman.common.Message;

/**
 *
 * @author oscar
 */
public class GameController implements Initializable {

    @FXML private HBox heartBox;
    @FXML private Button guessLetterButton;
    @FXML private Button guessWordButton;

    HeartComponent[] hearts;
    NetHandler net;

    public GameController(NetHandler net) {
        this.net = net;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        guessLetterButton.setOnAction(this::guessLetter);
        guessWordButton.setOnAction(this::guessWord);
    }

    private void guessLetter(ActionEvent event) {
        Message message = net.play();
        System.out.println(message.getType());
    }

    private void guessWord(ActionEvent event) {
        System.out.println("there");
    }

}

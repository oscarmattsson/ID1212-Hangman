/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarmat.kth.id1212.hangman.client.controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    @FXML private TextField guessLetterField;
    @FXML private TextField guessWordField;
    @FXML private Label scoreLabel;
    @FXML private Label wordLabel;

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

    /**
     * Guess a letter in the word.
     * @param event Triggered by action on guess letter button.
     */
    private void guessLetter(ActionEvent event) {
        Task<Message<Message[]>> task = new Task<Message<Message[]>>() {

            @Override
            protected Message<Message[]> call() throws Exception {
                char c = guessLetterField.getText().charAt(0);
                return net.guessLetter(c);
            }
        };
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                updateState(task.getValue());
            }
        });
        Thread thread = new Thread(task);
        thread.start();
    }

    private void guessWord(ActionEvent event) {
        System.out.println("there");
    }

    private void updateState(Message<Message[]> message) {

    }

}

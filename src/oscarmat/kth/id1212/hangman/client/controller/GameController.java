/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarmat.kth.id1212.hangman.client.controller;


import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import oscarmat.kth.id1212.hangman.client.net.NetHandler;
import oscarmat.kth.id1212.hangman.client.view.components.HeartComponent;
import oscarmat.kth.id1212.hangman.common.GameDTO;

/**
 *
 * @author oscar
 */
public class GameController implements Initializable {

    @FXML private HBox heartBox;
    @FXML private VBox guessBox;
    @FXML private Button guessLetterButton;
    @FXML private Button guessWordButton;
    @FXML private TextField guessLetterField;
    @FXML private TextField guessWordField;
    @FXML private Label scoreLabel;
    @FXML private Label wordLabel;
    @FXML private Label statusLabel;

    NetHandler net;

    private final GameController self;

    /**
     * Set up controller for game view.
     * @param net Connection to server.
     */
    public GameController(NetHandler net) {
        self = this;
        self.net = net;
    }

    /**
     * Entry point for the game view.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newGame();
        guessLetterButton.setOnAction(self::guessLetter);
        guessWordButton.setOnAction(self::guessWord);
    }

    private void newGame() {
        Task<GameDTO> newGameTask = new Task<GameDTO>() {
            @Override
            protected GameDTO call() throws Exception {
                updateMessage("Starting new game...");
                GameDTO gameState = net.newGame();
                updateMessage("Game started!");
                return gameState;
            }
        };


        statusLabel.textProperty().bind(newGameTask.messageProperty());
        newGameTask.setOnSucceeded(this::onNewGame);

        Thread thread = new Thread(newGameTask);
        thread.start();
    }

    private void onNewGame(WorkerStateEvent event) {
        heartBox.getChildren().clear();
        updateState(event);
    }

    /**
     * Guess a letter in the word.
     * @param event Triggered by action on guess letter button.
     */
    private void guessLetter(ActionEvent event) {
        String word = guessLetterField.getText();
        guessLetterField.setText("");
        if(word.length() > 0) {
            Task<GameDTO> guessLetterTask = new Task<GameDTO>() {
                @Override
                protected GameDTO call() throws Exception {
                    char letter = word.charAt(0);
                    updateMessage("Waiting...");
                    GameDTO gameState = net.guessLetter(letter);
                    updateMessage("Finished!");
                    return gameState;
                }
            };

            statusLabel.textProperty().bind(guessLetterTask.messageProperty());
            guessLetterTask.setOnSucceeded(this::updateState);

            Thread thread = new Thread(guessLetterTask);
            thread.start();
        }
    }

    /**
     * Guess a whole word.
     * @param event Triggered by action on guess word button.
     */
    private void guessWord(ActionEvent event) {
        String word = guessWordField.getText();
        guessWordField.setText("");
        if(word.length() > 0) {
            Task<GameDTO> guessWordTask = new Task<GameDTO>() {
                @Override
                protected GameDTO call() throws Exception {
                    updateMessage("Waiting...");
                    GameDTO gameState = net.guessWord(word);
                    updateMessage("Finished!");
                    return gameState;
                }
            };

            statusLabel.textProperty().bind(guessWordTask.messageProperty());
            guessWordTask.setOnSucceeded(this::updateState);

            Thread thread = new Thread(guessWordTask);
            thread.start();
        }
    }

    private void updateState(WorkerStateEvent event) {
        GameDTO gameState = (GameDTO)event.getSource().getValue();

        if(gameState.isGameOver()) {
            newGame();
        }
        else {
            int max = gameState.getMaximumAllowedAttempts();
            int failed = gameState.getFailedAttempts();
            updateHearts(max, failed);

            Map<String, Boolean> guesses = gameState.getGuesses();
            updateGuesses(guesses);

            scoreLabel.setText("Score: " + Integer.toString(gameState.getScore()));
            wordLabel.setText(gameState.getWordState());
        }
    }

    /**
     * Update the amount of dead hearts.
     * @param total Total amount of hearts.
     * @param dead Amount of dead hearts.
     */
    private void updateHearts(int total, int dead) {
        List<Node> hearts = heartBox.getChildren();

        if(hearts.isEmpty()) {
            hearts.clear();
            for (int i = 0; i < total; i++) {
                HeartComponent heart = new HeartComponent();
                heartBox.getChildren().add(heart);
            }
        }
        for(int i = total - 1; i > (total - 1) - dead; i--) {
            ((HeartComponent)hearts.get(i)).setDeadProperty(true);
        }
    }

    private void updateGuesses(Map<String, Boolean> guesses) {
        guessBox.getChildren().clear();
        for (Map.Entry<String, Boolean> entry : guesses.entrySet()) {
            Label guessLabel = new Label();
            guessLabel.setTextFill(entry.getValue() ? Color.LIME : Color.RED);

            String guess = entry.getKey();
            String result = (entry.getValue() ? "Correct" : "Wrong");
            guessLabel.setText(guess + ", " + result);

            guessBox.getChildren().add(guessLabel);
        }
    }
}

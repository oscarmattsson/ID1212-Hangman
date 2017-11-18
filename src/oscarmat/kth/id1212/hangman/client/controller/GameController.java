/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarmat.kth.id1212.hangman.client.controller;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import oscarmat.kth.id1212.hangman.client.net.NetHandler;
import oscarmat.kth.id1212.hangman.client.view.components.HeartComponent;
import oscarmat.kth.id1212.hangman.common.GameState;

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
    @FXML private Label statusLabel;

    HeartComponent[] hearts;
    NetHandler net;

    private final GameController self;

    public GameController(NetHandler net) {
        self = this;
        self.net = net;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newGame();
        guessLetterButton.setOnAction(self::guessLetter);
        guessWordButton.setOnAction(self::guessWord);
    }

    private void newGame() {
        Task<GameState> newGameTask = new Task<GameState>() {
            @Override
            protected GameState call() throws Exception {
                updateMessage("Starting new game...");
                GameState gameState = net.newGame();
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
        updateState(event);
        updateScore();
    }

    private void updateScore() {
        Task<Integer> fetchScoreTask = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                updateMessage("Fetching score...");
                int score =  net.getScore();
                updateMessage("Updating score...");
                return score;
            }
        };

        scoreLabel.textProperty().bind(fetchScoreTask.messageProperty());
        fetchScoreTask.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                scoreLabel.textProperty().unbind();

                int score = (int)event.getSource().getValue();
                scoreLabel.setText(Integer.toString(score));
            }
        });

        Thread thread = new Thread(fetchScoreTask);
        thread.start();
    }

    /**
     * Guess a letter in the word.
     * @param event Triggered by action on guess letter button.
     */
    private void guessLetter(ActionEvent event) {
        String word = guessLetterField.getText();
        if(word.length() > 0) {
            Task<GameState> guessLetterTask = new Task<GameState>() {
                @Override
                protected GameState call() throws Exception {
                    char letter = word.charAt(0);
                    updateMessage("Waiting...");
                    GameState gameState = net.guessLetter(letter);
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
        if(word.length() > 0) {
            Task<GameState> guessWordTask = new Task<GameState>() {
                @Override
                protected GameState call() throws Exception {
                    updateMessage("Waiting...");
                    GameState gameState = net.guessWord(word);
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
        GameState gameState = (GameState)event.getSource().getValue();
        if(gameState.isGameOver()) {
            if(gameState.isGameWon()) {
                // TODO: Show win screen
            }
            else if(gameState.isGameLost()) {
                // TODO: Show lose screen
            }
        }
        else {
            int failed = gameState.getFailedAttempts();
            int max = gameState.getMaximumAllowedAttempts();

            if(heartBox.getChildren().isEmpty()) {
                for (int i = max; i > failed; i--) {
                    HeartComponent heart = new HeartComponent();
                    heartBox.getChildren().add(heart);
                }
                for (int i = failed; i > 0; i--) {
                    HeartComponent heart = new HeartComponent();
                    heart.setDeadProperty(true);
                    heartBox.getChildren().add(heart);
                }
            }
            else {
                List<Node> hearts = heartBox.getChildren();
                for(int i = max - 1; i > (max - 1) - failed; i--) {
                    ((HeartComponent)hearts.get(i)).setDeadProperty(true);
                }
            }
            wordLabel.setText(gameState.getWordState());
            System.out.println(gameState.getWordState());
            System.out.println(gameState.getFailedAttempts());
        }
    }
}

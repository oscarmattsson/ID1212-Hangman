package oscarmat.kth.id1212.hangman.client.controller;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import oscarmat.kth.id1212.hangman.client.net.NetHandler;

import javafx.event.ActionEvent;
import oscarmat.kth.id1212.hangman.client.view.View;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IntroController implements Initializable{

    @FXML private TextField hostField;
    @FXML private TextField portField;
    @FXML private Button connectButton;
    @FXML private Label statusLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectButton.setOnAction(this::connect);
    }

    /**
     * Connect to the server and update the state of the GUI.
     * @param event Triggered when clicking the connect button.
     */
    private void connect(ActionEvent event) {
        Task<NetHandler> connectTask = new Task<NetHandler>() {
            @Override
            protected NetHandler call() throws Exception {
                try {
                    updateMessage("Connecting...");
                    String host = hostField.getText();
                    int port = Integer.parseInt(portField.getText());
                    Window window = connectButton.getScene().getWindow();
                    NetHandler net = new NetHandler(host, port, window);
                    return net;
                }
                catch (Exception e) {
                    updateMessage("Error connecting to server.");
                    throw e;
                }
            }
        };

        statusLabel.textProperty().bind(connectTask.messageProperty());
        connectTask.setOnSucceeded(this::startGame);

        Thread thread = new Thread(connectTask);
        thread.start();
    }

    /**
     * Change the view to game after successfully connecting to the server.
     * @param event Triggered after connecting to the server.
     */
    private void startGame(WorkerStateEvent event) {
        NetHandler net = (NetHandler)event.getSource().getValue();
        FXMLLoader game = new FXMLLoader(getClass().getResource(View.GAME));
        GameController controller = new GameController(net);
        game.setController(controller);

        try {
            Parent root = game.load();
            connectButton.getScene().setRoot(root);
        }
        catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

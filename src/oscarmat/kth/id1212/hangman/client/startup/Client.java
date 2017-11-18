/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarmat.kth.id1212.hangman.client.startup;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oscarmat.kth.id1212.hangman.client.controller.IntroController;
import oscarmat.kth.id1212.hangman.client.view.View;

/**
 *
 * @author oscar
 */
public class Client extends Application {

    /**
     * Entry point for the client.
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader intro = new FXMLLoader(getClass().getResource(View.INTRO));
        IntroController introController = new IntroController();
        intro.setController(introController);

        Parent root = intro.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setMinHeight(400);
        stage.setMinWidth(600);

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

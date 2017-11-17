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
import oscarmat.kth.id1212.hangman.client.controller.GameController;
import oscarmat.kth.id1212.hangman.client.net.NetHandler;

/**
 *
 * @author oscar
 */
public class Client extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/oscarmat/kth/id1212/hangman/client/view/Game.fxml"));

        NetHandler net = new NetHandler("127.0.0.1", 1337);
        GameController controller = new GameController(net);

        loader.setController(controller);
        Parent root = loader.load();
        
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

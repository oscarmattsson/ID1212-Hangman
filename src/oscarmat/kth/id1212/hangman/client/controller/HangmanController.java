/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oscarmat.kth.id1212.hangman.client.controller;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import oscarmat.kth.id1212.hangman.client.net.NetHandler;
import oscarmat.kth.id1212.hangman.client.view.components.HeartComponent;

/**
 *
 * @author oscar
 */
public class HangmanController implements Initializable {

    @FXML private HBox heartBox;

    HeartComponent[] hearts;
    NetHandler net;

    public HangmanController(NetHandler net) {
        this.net = net;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HeartComponent heart = new HeartComponent();
        heartBox.getChildren().add(heart);
        HeartComponent deadHeart = new HeartComponent();
        heartBox.getChildren().add(deadHeart);
        deadHeart.setDeadProperty(true);


    }

}

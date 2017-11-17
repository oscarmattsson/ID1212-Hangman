package oscarmat.kth.id1212.hangman.client.view.components;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Pane with an image of a heart which has two states;
 * regular (not dead) and dead.
 */
public class HeartComponent extends Pane {

    @FXML private ImageView heartView;

    private Image heart;
    private Image deadHeart;

    private BooleanProperty dead;

    /**
     * Initialize a heart component displaying the default
     * regular heart image (as opposed to dead).
     */
    public HeartComponent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("heart_component.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        heart = new Image("oscarmat/kth/id1212/hangman/client/resources/heart.png");
        deadHeart = new Image("oscarmat/kth/id1212/hangman/client/resources/deadheart.png");

        dead = new SimpleBooleanProperty(this, "Dead");

        setDeadProperty(false);
    }

    /**
     * Decides whether the image of the heart should display the dead version
     * or the regular version.
     * @return true if the heart is displaying as dead, false otherwise.
     */
    public BooleanProperty deadProperty() {
        return dead;
    }

    /**
     * @return true if the heart is displaying as dead, false otherwise.
     */
    public Boolean getDeadProperty() {
        return dead.getValue();
    }

    /**
     * Set whether the image of the heart should display the dead version
     * or the regular version.
     * @param dead true for regular heart, false for dead heart.
     */
    public void setDeadProperty(Boolean dead) {
        this.dead.setValue(dead);
        if(!dead) {
            heartView.setImage(heart);
        }
        else {
            heartView.setImage(deadHeart);
        }
    }
}

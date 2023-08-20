/**
The Bomb class represents the bomb item in the game.
It extends the Items class and overrides the wasEaten method.
@author Pavel
@version 1.0
*/

package application;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import snappleGUI.GameWindow;

public class Bomb extends Items {
    private String audio = "src/application/img/Bomb.mp3";
    private Media media = new Media(new File(audio).toURI().toString());
    private	MediaPlayer mediaPlayer = new MediaPlayer(media);
	/**
	 * Constructor for the Bomb class.
	 * It takes in a GameWindow and GraphicsContext object as parameters.
	 *
	 * @param gw The GameWindow object.
	 * @param gc The GraphicsContext object.
	 */
    public Bomb(GameWindow gw, GraphicsContext gc) {
        super(gw, gc);
        this.itemImage = new Image("application\\img\\Bomb.png");
    }

    /**
     * Overrides the wasEaten method from the Items class.
     * This method is called when the snake eats the bomb.
     * It sets the wasEaten flag to true and calls the gameOver method of the GameWindow object.
     *
     * @param sn The Snake object that ate the bomb.
     * @param gw The GameWindow object.
     */
    public void wasEaten(Snake sn, GameWindow gw) {
        setWasEaten(true);
        if(Settings.getInstance().isSoundOn()==true)
        mediaPlayer.play();
        gw.gameOver();
    }
}

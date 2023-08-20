/**
The GreenApple class represents the green apple item in the game.
It extends the Items class and overrides the wasEaten method.
@author Pavel
@version 1.0
*/

package application;

import java.awt.Point;
import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import snappleGUI.GameWindow;

public class GreenApple extends Items {
    private String audio = "src/application/img/Apple.mp3";
    private Media media = new Media(new File(audio).toURI().toString());
    private	MediaPlayer mediaPlayer = new MediaPlayer(media);
	/**
	 * Constructor for the GreenApple class.
	 * It takes in a GameWindow and GraphicsContext object as parameters.
	 *
	 * @param gw The GameWindow object.
	 * @param gc The GraphicsContext object.
	 */
    public GreenApple(GameWindow gw, GraphicsContext gc) {
        super(gw, gc);
        this.itemImage = new Image("application\\img\\GreenApple.png");
    }
    /**
     * Overrides the wasEaten method from the Items class.
     * This method is called when the snake eats the green apple.
     * It increases the snake's score by 1 and adds a new point to the snake's body.
     *
     * @param sn The Snake object that ate the green apple.
     * @param gw The GameWindow object.
     */
    public void wasEaten(Snake sn, GameWindow gw) {
        sn.setScore(sn.getScore() + 1);
        if(Settings.getInstance().isSoundOn()==true)
        mediaPlayer.play();
        sn.getSnakeBody().add(new Point(-1, -1));
    }

}
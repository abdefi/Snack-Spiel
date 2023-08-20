/**
The RedApple class extends the Items class and represents a red apple item in the game, when the snake eats it, the score increases by 100.
@author Pavel
*/
package application;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import snappleGUI.GameWindow;

public class RedApple extends Items {
	 private String audio = "src/application/img/Apple.mp3";
	 private Media media = new Media(new File(audio).toURI().toString());
	 private MediaPlayer mediaPlayer = new MediaPlayer(media);
	/**
	 * Constructor for the RedApple class.
	 * 
	 * @param gw The game window where the red apple will be drawn.
	 * @param gc The graphics context used to draw the red apple on the game window.
	 */
    public RedApple(GameWindow gw, GraphicsContext gc) {
        super(gw, gc);
        this.itemImage = new Image("application\\img\\RedApple.png");
    }
    /**
     * Increases the score by 100 when the snake eats the red apple.
     * 
     * @param sn The snake that eats the red apple.
     * @param gw The game window where the red apple was drawn.
     */
    public void wasEaten(Snake sn, GameWindow gw) {
        sn.setScore(sn.getScore() + 100);
        if(Settings.getInstance().isSoundOn()==true)
        	mediaPlayer.play();
        	
    }

}

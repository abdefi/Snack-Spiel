package snappleGUI;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Die Klasse Window erweitert die Klasse Stage und dient als Basisklasse für
 * Fenster in der Anwendung. Sie bietet Funktionalität zur Einrichtung der
 * Stage, wie z.B. dem Setzen eines Titels und der Einstellung, ob die Größe der
 * Stage verändert werden kann.
 */
public abstract class Window extends Stage {
    Timeline timeline;
    public Image icon = new Image("application/img/snake.png");

    /*
     * Erstellt eine neue Instanz von Window.
     */
    public Window() {
    	
    //	Image icon = new Image("application/img/snake.png");


    	// Set the icon for all windows
    	
        this.getIcons().add(icon);

        // Set up the stage
        this.setTitle("Game");
        this.setResizable(false);
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }
}

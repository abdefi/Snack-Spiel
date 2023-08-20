/**

The Main class represents the entry point of the program, it extends the javafx.application.Application class and override the start method to launch the Welcome Window.
@author Team_G
@version 1.0
*/
package application;

import javafx.application.Application;
import javafx.stage.Stage;
import snappleGUI.WelcomeWindow;

public class Main extends Application {

	/**
	 * This method is the entry point of the program, it launches the Welcome Window.
	 * 
	 * @param primaryStage The primary stage of the program.
	 */
    public void start(Stage primaryStage) {
        WelcomeWindow g = new WelcomeWindow();
        g.show();
    }

    /**
     * The main method of the program.
     * 
     * @param args Command line arguments passed to the program.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

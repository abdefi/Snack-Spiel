package snappleGUI;

import java.io.IOException;

import application.Settings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Diese Klasse stellt das Willkomen-Fester dar. Hier kann der User: seinen
 * Namen eingeben, Schwierigkeit einstellen und Spiel starten
 * <p>
 * Es gibt die Möglichkeit zwischen 3 Schwierigkeiten auswählen: easy, medium
 * und hard.
 *
 * @author Abdellah Filali
 */
public class WelcomeWindow extends Window {

    private Button exitButton;
    private Button settingsButton;
    private ToggleButton easyButton;
    private ToggleButton mediumButton;
    private ToggleButton hardButton;
    private Button okButton;
    private ToggleGroup modus;
    private TextField nameEingabe;

    private Image image = new Image("application/img/An.jpg");
    public WelcomeSettingWindow setting = new WelcomeSettingWindow();
    
    /**
     * Hier geht es um den Konstruktor der Klasse. Die Button ,sowie die Anleitung,
     * werden in den richtigen Stellen positioniert und die verschiedenen Actions
     * von den Buttons eingestellt
     *
     * @author Abdellah Filali
     */
    public WelcomeWindow() {
        // Buttons
        okButton = new Button("Ok");
        settingsButton = new Button("Settings");
        exitButton = new Button("Exit");
        easyButton = new ToggleButton("Easy");
        easyButton.setSelected(true);
        mediumButton = new ToggleButton("Medium");
        hardButton = new ToggleButton("Hard");
        nameEingabe = new TextField();
        // Create label
        Label name = new Label("NAME: ");
        name.setStyle("-fx-font-size: 30; -fx-text-fill: black");

        // Create label
        Label difficulty = new Label("SCHWIERIGKEIT ");
        difficulty.setStyle("-fx-font-size: 30; -fx-text-fill: black");

        // Create top bar container
        ImageView imageView = new ImageView(image);
        imageView.setX(20);
        imageView.setY(20);
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);
        // Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        // Creating a Group object
        Group image = new Group(imageView);

        // Group setting
        modus = new ToggleGroup();

        easyButton.setToggleGroup(modus);

        mediumButton.setToggleGroup(modus);

        hardButton.setToggleGroup(modus);

        // Create button container
        HBox buttonBoxName = new HBox(name, nameEingabe);
        buttonBoxName.setSpacing(20);

        // Create layout container
        HBox buttonBoxOkExit = new HBox(okButton, exitButton);
        buttonBoxOkExit.setAlignment(Pos.BASELINE_RIGHT);
        buttonBoxOkExit.setSpacing(10);

        // Create layout container
        HBox buttonBoxDifficulty = new HBox(easyButton, mediumButton, hardButton);
        buttonBoxDifficulty.setAlignment(Pos.BASELINE_RIGHT);
        buttonBoxDifficulty.setSpacing(30);

        Pane root = new Pane(buttonBoxName, buttonBoxOkExit, buttonBoxDifficulty, image, difficulty, settingsButton);

        image.setLayoutX(15);
        image.setLayoutY(15);

        buttonBoxName.setLayoutX(450);
        buttonBoxName.setLayoutY(100);

        difficulty.setLayoutX(450);
        difficulty.setLayoutY(200);

        buttonBoxDifficulty.setLayoutX(500);
        buttonBoxDifficulty.setLayoutY(300);

        buttonBoxOkExit.setLayoutX(450);
        buttonBoxOkExit.setLayoutY(500);

        settingsButton.setLayoutX(700);
        settingsButton.setLayoutY(0);

        // Create scene and set stage properties
        Scene scene = new Scene(root, 800, 600);
        setTitle("Snapple");
        setScene(scene);

        // Add event handler to easy button
        easyButton.setOnAction(event -> {
            Settings.getInstance().setDifficulty("Easy");
        });

        // Add event handler to medium button
        mediumButton.setOnAction(event -> {
            Settings.getInstance().setDifficulty("Medium");
        });

        // Add event handler to hard button
        hardButton.setOnAction(event -> {
            Settings.getInstance().setDifficulty("Hard");
        });

        // Add event handler to setting button
        settingsButton.setOnAction(event -> {
            // Open the settings window
            if (setting == null) {
                setting = new WelcomeSettingWindow();
            } else {
                setting.show();
            }
        });

        // Add event handler to OK Button
        okButton.setOnAction(event -> {
            if(!nameEingabe.getText().isEmpty()) {
            	GameWindow game = new GameWindow(getNameEingabe());
                Stage stage = (Stage) okButton.getScene().getWindow();
                game.show();
                stage.close();   
            } 
            else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("You cannot play without entering a name");
                alert.setContentText("Please give a name");
                alert.showAndWait();
            }
        });

        // Add event handler to exit button
        exitButton.setOnAction(event -> {
            close();
        });

    }

    public String getNameEingabe() {
        return nameEingabe.getText();
    }
}

package snappleGUI;

import application.Settings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Diese Klasse ist für die Einstellungen in dem Willkomme-Fenster zuständig.
 * Hier ist es möglich den Theme von 3 auszuwählen und Ton/Musik an-/ausschalten
 *
 * @author Abdellah Filali
 */

public class WelcomeSettingWindow extends Window {
    private static SettingsWindow instance;
    private ToggleButton musicButton;
    private ToggleButton soundButton;
    private ToggleButton hell;
    private ToggleButton dark;
    private ToggleButton halloween;
    private ToggleGroup themen;
    private String theme;

    /**
     * Das ist der Konstruktor der Klasse: hier werden die Button initialisiert und
     * positioniert. Schließlich werden die Actions eingestellt
     *
     * @author Abdellah Filali
     */
    public WelcomeSettingWindow() {

        this.setWidth(400);
        this.setHeight(300);

        // Create the music toggle button
        musicButton = new ToggleButton();
        updateMusicButton();
        musicButton.setOnAction(event -> {
            Settings.getInstance().setMusicOn(!Settings.getInstance().isMusicOn());
            updateMusicButton();
        });

        // Create the sound toggle button
        soundButton = new ToggleButton();
        updateSoundButton();
        soundButton.setOnAction(event -> {
            Settings.getInstance().setSoundOn(!Settings.getInstance().isSoundOn());
            updateSoundButton();
        });

        // Create the OK button
        Button okButton = new Button("Ok");

        // Create the Theme buttons
        Label theme = new Label("Theme");
        theme.setStyle("-fx-font-size: 30; -fx-text-fill: black");
        hell = new ToggleButton("Hell");
        hell.setSelected(true);
        dark = new ToggleButton("Dark");
        halloween = new ToggleButton("Halloween");

        // Group settings
        themen = new ToggleGroup();
        hell.setToggleGroup(themen);
        dark.setToggleGroup(themen);
        halloween.setToggleGroup(themen);

        // Box
        HBox box = new HBox(dark, hell, halloween);
        box.setSpacing(10);
        box.setPadding(new Insets(10));
        box.setAlignment(Pos.CENTER);

        // Event-Handler for Theme buttons
        hell.setOnAction(event -> {
            Settings.getInstance().setTheme("hell");
        });
        dark.setOnAction(event -> {
            Settings.getInstance().setTheme("dark");
        });
        halloween.setOnAction(event -> {
            Settings.getInstance().setTheme("halloween");
        });

        // Event-Handler for Ok button
        okButton.setOnAction(event -> {
            Settings.getInstance().save();
            this.close();
        });

        // Add the controls to the layout
        VBox layout = new VBox(theme, box, musicButton, soundButton);
        layout.setSpacing(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        okButton.setAlignment(Pos.BOTTOM_CENTER);

        // Set the scene and show the stage
        Scene scene = new Scene(layout);
        this.setScene(scene);
        this.setTitle("Settings");
        this.setResizable(false);
    }

    /**
     * Diese Methode gibt die Instance von einem Objekt der Klasse SettingWindow
     * zurück.
     *
     * @return instance SettingWindow
     * @see SettingWindow
     */

    public static SettingsWindow getInstance() {
        if (instance == null) {
            instance = new SettingsWindow();
        }
        return instance;
    }

    /**
     * Diese Methode dient dazu, den Button für die Musik zu aktualisieren
     *
     * @see SettingWindow
     */

    private void updateMusicButton() {
        if (Settings.getInstance().isMusicOn()) {
            musicButton.setText("MUSIK AN");
            musicButton.setStyle("-fx-background-color: green; -fx-text-fill: white");
        } else {
            musicButton.setText("MUSIK AUS");
            musicButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
        }
        musicButton.setSelected(Settings.getInstance().isMusicOn());
    }

    /**
     * Diese Methode dient dazu, den Button für den Ton zu aktualisieren.
     *
     * @see SettingWindow
     */

    private void updateSoundButton() {
        if (Settings.getInstance().isSoundOn()) {
            soundButton.setText("TON AN");
            soundButton.setStyle("-fx-background-color: green; -fx-text-fill: white");
        } else {
            soundButton.setText("TON AUS");
            soundButton.setStyle("-fx-background-color: red; -fx-text-fill: white");
        }
    }

    /**
     * Mit dieser Methode wird das aktuelle Theme des Spiels gesetzt.
     *
     * @param theme Der Name des gewünschten Themes als String
     */

    public void setTheme(String theme) {
        this.theme = theme;
    }
}

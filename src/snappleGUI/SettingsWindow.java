package snappleGUI;

import application.Settings;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.VBox;

/**
 * Die Klasse SettingsWindow repräsentiert ein Fenster, in dem der Benutzer
 * seine Einstellungen für Musik und Ton ändern kann.
 * <p>
 * Sie enthält Schaltflächen zum Ein- und Ausschalten von Musik und Ton sowie
 * eine Schaltfläche zum Starten des Spiels.
 * <p>
 * Die Einstellungen werden beim Schließen des Fensters gespeichert.
 *
 * @author Kristian Kovács
 */

public class SettingsWindow extends Window {
    private static SettingsWindow instance;
    private ToggleButton musicButton;
    private ToggleButton soundButton;

    /**
     * Erstellt ein neues SettingsWindow-Objekt.
     */
    SettingsWindow() {
        this.setWidth(400);
        this.setHeight(300);

        // Musik-Toggle-Button erstellen
        musicButton = new ToggleButton();
        updateMusicButton();
        musicButton.setOnAction(event -> {
            Settings.getInstance().setMusicOn(!Settings.getInstance().isMusicOn());
            updateMusicButton();
        });

        // Sound-Toggle-Button erstellen
        soundButton = new ToggleButton();
        updateSoundButton();
        soundButton.setOnAction(event -> {
            Settings.getInstance().setSoundOn(!Settings.getInstance().isSoundOn());
            updateSoundButton();
        });

        // Play-Button erstellen
        Button playButton = new Button("Play");
        playButton.setOnAction(event -> {
            // Einstellungen speichern und Fenster schließen
            Settings.getInstance().save();
            this.close();
            timeline.play();
        });

        // Steuerelemente zum Layout hinzufügen
        VBox layout = new VBox(musicButton, soundButton, playButton);
        layout.setSpacing(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        // Szene setzen und Stage anzeigen
        Scene scene = new Scene(layout);
        this.setScene(scene);
        this.setTitle("Settings");
        this.setResizable(false);
    }

    /**
     * Gibt eine Instanz der SettingsWindow zurück.
     *
     * @return Die SettingsWindow-Instanz.
     */
    public static SettingsWindow getInstance() {
        if (instance == null) {
            instance = new SettingsWindow();
        }
        return instance;
    }

    /**
     * Aktualisiert die Anzeige der Musik-Schaltfläche.
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
     * Aktualisiert die Anzeige der Sound-Schaltfläche.
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
}

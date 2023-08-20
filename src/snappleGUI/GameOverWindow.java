package snappleGUI;

import application.HighScore;
import application.HighScoreTable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.scene.control.TableView;

/**
 * Die Klasse GameOverWindow repräsentiert ein Fenster, das angezeigt wird, wenn
 * das Spiel vorbei ist.
 * <p>
 * Es zeigt die Highscores an und ermöglicht dem Benutzer, das Spiel erneut zu
 * starten, zum Hauptmenü zurückzukehren oder das Spiel zu verlassen.
 *
 * @author Kristian Kovács
 */

public class GameOverWindow extends Window {

    private HighScoreTable highScoreTable;
    private TableView<HighScore> tableView;
    private Button restartButton;
    private Button exitButton;

    /**
     * Erstellt ein neues GameOverWindow-Objekt.
     *
     * @param highScoreTable Das HighScoreTable-Objekt, das die anzuzeigenden
     *                       Highscores enthält.
     */
    public GameOverWindow(HighScoreTable highScoreTable) {
        this.highScoreTable = highScoreTable;

        // Label erstellen
        Label gameOverLabel = new Label("Game Over");
        gameOverLabel.setStyle("-fx-font-size: 48; -fx-text-fill: red");

        // Tabelle erstellen
        tableView = new TableView<>(highScoreTable.getItems());
        tableView.getColumns().addAll(highScoreTable.getColumns());

        // Buttons erstellen
        restartButton = new Button("Restart");
        exitButton = new Button("Exit");

        // Event-Handler für den Neustart-Button hinzufügen
        restartButton.setOnAction(event -> {
            // Neues Spielfenster erstellen
            WelcomeWindow g = new WelcomeWindow();
            g.show();
            close();
        });

        // Event-Handler für den Beenden-Button hinzufügen
        exitButton.setOnAction(event -> {
            close();
        });

        // Button-Container erstellen
        HBox buttonBox = new HBox(restartButton, exitButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        // Layout-Container erstellen
        VBox root = new VBox(gameOverLabel, tableView, buttonBox);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);

        // Szene erstellen und Stage-Eigenschaften setzen
        Scene scene = new Scene(root, 400, 400);
        setTitle("Game Over");
        setScene(scene);
    }
}

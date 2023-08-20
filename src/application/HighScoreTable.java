package application;

import java.util.Arrays;
import java.util.List;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;

/**

Die Klasse HighScoreTable repräsentiert eine Tabelle für Highscores.
Sie enthält eine Liste mit HighScore-Einträgen (items), sowie drei Spalten für Platzierung (rankColumn), Name (nameColumn) und Score (scoreColumn).
Die HighScoreTable wird beim Erstellen mit den Highscores aus einer Datei initialisiert und die ersten 9 Einträge werden geladen.
Die Methode getItems() gibt die Liste der HighScore-Einträge zurück und die Methode getColumns() gibt die Spalten der Tabelle zurück.
*/

public class HighScoreTable {
    private ObservableList<HighScore> items;
    private TableColumn<HighScore, Integer> rankColumn;
    private TableColumn<HighScore, String> nameColumn;
    private TableColumn<HighScore, Integer> scoreColumn;
    

    public HighScoreTable() {
        // Initialize items and columns
        items = FXCollections.observableArrayList();
        rankColumn = new TableColumn<>("Platzierung");
        rankColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(items.indexOf(cellData.getValue()) + 1).asObject());
        nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty().asObject());
        
        // Load high scores from file
        items.addAll(HighScore.loadHighScores().subList(0, Math.min(9, HighScore.loadHighScores().size())));
    }


    public ObservableList<HighScore> getItems() {
        return items;
    }

    public List<TableColumn<HighScore, ?>> getColumns() {
        return Arrays.asList(rankColumn, nameColumn, scoreColumn);
    }
}

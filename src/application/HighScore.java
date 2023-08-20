package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**

Die Klasse HighScore repräsentiert einen Eintrag für einen Highscore, bestehend aus einem Namen (name) und einem Score (score).
Beide Eigenschaften sind als JavaFX Properties implementiert, um die Anbindung an eine Oberfläche zu erleichtern.
Es gibt Methoden zum Lesen und Schreiben dieser Eigenschaften sowie eine Property selbst zurückzugeben.
Innerhalb der Klasse gibt es eine nested ScoreComparator Klasse, welche dazu verwendet wird, um Highscores nach Score zu sortieren.
Es gibt auch statische Methoden saveHighScores() und loadHighScores(). saveHighScores() speichert eine Liste von Highscores in einer Datei im Home-Verzeichnis des Benutzers, während loadHighScores() Highscores aus dieser Datei lädt.
Beide Methoden sortieren die Highscores nach Score bevor sie sie speichern oder zurückgeben.
*/

public class HighScore {
    private StringProperty name;
    private SimpleIntegerProperty score;
    
    public static class ScoreComparator implements Comparator<HighScore> {
        @Override
        public int compare(HighScore o1, HighScore o2) {
            return o2.getScore() - o1.getScore();
        }
    }

    public HighScore(String name, int score) {
        this.name = new SimpleStringProperty(name);
        this.score = new SimpleIntegerProperty(score);
       
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    
    public StringProperty nameProperty() {
        return name;
    }

    public int getScore() {
        return score.get();
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }
    //Speichert Highscore in Datei "highscores.txt"
    public static void saveHighScores(ArrayList<HighScore> highScores) {
        String homeDir = System.getProperty("user.home");
        String fileName = homeDir + File.separator + "highscores.txt";

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            highScores.sort(Comparator.comparing(HighScore::getScore).reversed());

            for (HighScore highScore : highScores) {
                bw.write(highScore.getName() + "," + highScore.getScore());
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //läd Highscores aus Datei "highscores.txt"
    public static ArrayList<HighScore> loadHighScores() {
    	ArrayList<HighScore> highScores = new ArrayList<>();
        String homeDir = System.getProperty("user.home");
        String fileName = homeDir + File.separator + "highscores.txt";

        try {
            File file = new File(fileName);
            if (!file.exists()) {
                return highScores;
            }

            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(",");
                highScores.add(new HighScore(line[0], Integer.parseInt(line[1])));
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        highScores.sort(Comparator.comparing(HighScore::getScore).reversed());
        return highScores;
    }
}

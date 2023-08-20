package snappleGUI;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import application.HighScore.ScoreComparator;
import application.Bomb;
import application.GreenApple;
import application.HighScore;
import application.HighScoreTable;
import application.Items;
import application.RedApple;
import application.Settings;
import application.Snake;

/**
 * Erstellt ein neues GameWindow-Objekt und initialisiert das Snake-Spiel.
 * <p>
 * Das GameWindow enthält eine Leiste mit einem Pause-Button und einem
 * Score-Label.
 * <p>
 * Es enthält außerdem eine Zeichenfläche, auf der das Snake-Spiel dargestellt
 * wird.
 * <p>
 * Das Spiel kann durch Tasteneingaben gesteuert werden und wird durch eine
 * Timeline animiert.
 * <p>
 * Wenn das Spiel beendet ist, wird ein GameOverWindow geöffnet.
 */

public class GameWindow extends Window {
    private Label scoreLabel;
    private Button pauseButton;
    private SettingsWindow settingsWindow;
    private int difficulty = 350;



    // Konstanten für die Größe und das Aussehen des Spiels
    private static final int WIDTH = 800;
    private static final int HEIGTH = 520;
    private static final int ROWS = 20;
    private static final int COLUMNS = 13;
    private static final int SQUARE_SIZE = WIDTH / ROWS;

    // Variablen für das Snake-Spiel
    private GraphicsContext gc;
    HighScoreTable highScoreTable = new HighScoreTable();
    List<Items> items = new ArrayList<>();
    GameOverWindow gameOver;
    Snake snake = new Snake();
    Scene scene;
    private int itemsSpawnRate = 100; // 100 to get the % of an item to be drawn

    /**
     * Erstellt ein neues GameWindow-Objekt und initialisiert das Snake-Spiel.
     */
    public GameWindow(String name) {
    	
    	// speed of game depend on difficulty
    	switch (Settings.getInstance().getDifficulty()) {
        case "Easy":
            difficulty = 250;
            break;
        case "Medium":
             difficulty = 200;
            break;
        case "Hard":
            difficulty = 120;
            break;
    }

        // Erstelle Score-Label
        scoreLabel = new Label(Integer.toString(snake.getScore()));
        scoreLabel.setStyle("-fx-font-size: 24; -fx-text-fill: white");

        // Erstelle Pause-Button
        pauseButton = new Button("Pause");
        pauseButton.setStyle("-fx-font-size: 24; -fx-text-fill: white");
        pauseButton.setOnAction(event -> {
            // Öffne das Einstellungsfenster
            if (settingsWindow == null) {
                settingsWindow = new SettingsWindow();
                settingsWindow.show();
                settingsWindow.setTimeline(timeline);
                timeline.pause();
            } else {
                settingsWindow.show();
                settingsWindow.setTimeline(timeline);
                timeline.pause();
            }
        });

        // Erstelle Leiste
        HBox topBar = new HBox(pauseButton, scoreLabel);
        topBar.setAlignment(Pos.CENTER);
        topBar.setSpacing(10);
        topBar.setPadding(new Insets(14));
        topBar.setStyle("-fx-background-color: black");

        // Erstelle Layout-Container
        BorderPane root = new BorderPane();
        root.setBottom(topBar);

        // Erstelle Zeichenfläche für das Snake-Spiel
        Canvas canvas = new Canvas(WIDTH, HEIGTH);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        // Erstelle Szene und setze Stage-Eigenschaften
        scene = new Scene(root, 800, 600);
        setTitle("Snapple");
        setScene(scene);
        // snake generation
        snake.snakeGeneration();
        snake.setName(name);

        // Erstelle Timeline für die Animation des Snake-Spiels
        timeline = new Timeline(new KeyFrame(Duration.millis(difficulty), e -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    /**

    The method run is responsible for updating the game state, it draws the background, handles the key events, creates and draws items,
    moves the snake, checks for collision between the snake and the items, and updates the score label.
    @param gc The graphics context used to draw the game elements on the game window.
    */
    private void run(GraphicsContext gc) {
        drawBackground(gc);
        scene.setOnKeyPressed(KeyEvent -> {
            snake.handle(KeyEvent);
        });
        itemsCreation();
        drawItems(items);
        snake.drawSnake(gc);
        for (int i = snake.getSnakeBody().size() - 1; i >= 1; i--) {
            snake.getSnakeBody().get(i).x = snake.getSnakeBody().get(i - 1).x;
            snake.getSnakeBody().get(i).y = snake.getSnakeBody().get(i - 1).y;
        }
        snake.move();
        snake.itemsCheck(items, this);
        scoreLabel.setText("Score: " + snake.getScore());
        itemsSpawnRate++;
    }
    /**
    The method drawBackground is responsible for drawing the background of the game window.
    It loops through each row and column and fills them with alternating colors to create a checkerboard pattern.
    @param gc The graphics context used to draw the background on the game window.
    */
    private void drawBackground(GraphicsContext gc) {
        String color1, color2 ; 
    	// draw background depend on Setting
        switch (Settings.getInstance().getTheme()) {
        case "dark":
            color1 = "DARKSLATEGREY";
            color2 = "GRAY";
            break;
        case "halloween":
            color1 = "#FFB966";
            color2 = "#FFCA8C";
            break;
        default:
            color1 = "AAD750"; 
            color2 = "A2D148";
            break;
    }	
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if ((i + j) % 2 == 0) {
                    gc.setFill(Color.web(color1));
                } else {
                    gc.setFill(Color.web(color2));
                }
                gc.fillRect(i * getSquareSize(), j * getSquareSize(), getSquareSize(), getSquareSize());
            }
        }
    }
    /**
    The method gameOver is responsible for showing the game over window.
    It also update the high score table if the current score isn't already there.
    */
    public void gameOver() {
        HighScore highScore = new HighScore(snake.getName(), snake.getScore());
        highScoreTable.getItems().add(highScore);
        Collections.sort(highScoreTable.getItems(), new ScoreComparator());
        HighScore.saveHighScores(new ArrayList<>(highScoreTable.getItems()));
        if (gameOver == null) {
            gameOver = new GameOverWindow(highScoreTable);
            gameOver.show();
        } else {
            gameOver.show();
        }
        timeline.pause();
        close();
    }
    /**
    The method itemsCreation is responsible for creating new items, randomly selecting their type and spawning them on the game window.
    It uses random number generation to determine the type of item and the spawn rate.
    */
    private void itemsCreation() {
        int y = (int) (Math.random() * itemsSpawnRate);
        int x = (int) (Math.random() * 100);
        if (y > 50) {
            if (x < 10) {
                Items item = new Bomb(this, gc);
                item.drawItem(gc);
            } 
            if (x < 30) {
                Items item = new GreenApple(this, gc);
                item.drawItem(gc);
            } 
            if (x <= 5) {
                Items item = new RedApple(this, gc);
                item.drawItem(gc);
            }
        }
    }
    /**
    The method drawItems is responsible for drawing all the items present in the game.
    @param items is a list of items to be drawn on the game window.
    */
    private void drawItems(List<Items> items) {
        for (Items item : items)
            item.drawItem(gc);
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public static int getRows() {
        return ROWS;
    }

    public static int getColumns() {
        return COLUMNS;
    }

    public static int getSquareSize() {
        return SQUARE_SIZE;
    }

    public List<Items> getItems() {
        return items;
    }

    public Button getPauseButton() {
        return pauseButton;
    }

}

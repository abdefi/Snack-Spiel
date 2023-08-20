/**
The Snake class represents the snake in the game, it handles the snake movements and collision detection.
@author Pavel
@version 1.0
*/
package application;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import snappleGUI.GameWindow;

public class Snake {
    private List<Point> snakeBody = new ArrayList();
    private String name;
    private Point snakeHead = new Point(0, 0);
    private int currentDirection = 0;
    private int score = 0;

    // Konstanten für die Bewegungsrichtungen
    private final int RIGHT = 0;
    private final int LEFT = 1;
    private final int UP = 2;
    private final int DOWN = 3;
    /**
     * Generate the snake with its body and set the snake head.
     */
    public void snakeGeneration() {
        for (int i = 0; i < 3; i++)
            snakeBody.add(new Point(0, 0));
        setSnakeHead(snakeBody.get(0));
    }
    /**
     * Draw the snake on the game window.
     * 
     * @param gc The graphics context used to draw the snake on the game window.
     */
    public void drawSnake(GraphicsContext gc) {
        String color; 
    	// draw snqke depend on Setting
        switch (Settings.getInstance().getTheme()) {
        case "dark":
            color = "LIGHTGRAY";
            
            break;
        case "halloween":
            color = "#660066";
            
            break;
        default:
            color = "4674E9"; 
           
            break;
    }
        gc.setFill(Color.web(color));
        gc.fillRoundRect(getSnakeHead().getX() * GameWindow.getSquareSize(), getSnakeHead().getY() * GameWindow.getSquareSize(),
                GameWindow.getSquareSize() - 1, GameWindow.getSquareSize() - 1, 35, 35);
        for (int i = 1; i < getSnakeBody().size(); i++)
            gc.fillRoundRect(getSnakeBody().get(i).getX() * GameWindow.getSquareSize(),
                    getSnakeBody().get(i).getY() * GameWindow.getSquareSize(), GameWindow.getSquareSize() - 1, GameWindow.getSquareSize() - 1, 20, 20);
    }
    
    /**
     * Handle the key events and set the current direction of the snake.
     * 
     * @param event The key event that is triggered by the user.
     */
    public void handle(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code == KeyCode.RIGHT || code == KeyCode.D) {
            if (getCurrentDirection() != LEFT) {
                setCurrentDirection(RIGHT);
            }
        } else if (code == KeyCode.LEFT || code == KeyCode.A) {
            if (getCurrentDirection() != RIGHT) {
                setCurrentDirection(LEFT);
            }
        } else if (code == KeyCode.KP_UP || code == KeyCode.W) {
            if (getCurrentDirection() != DOWN) {
                setCurrentDirection(UP);
            }
        } else if (code == KeyCode.DOWN || code == KeyCode.S) {
            if (getCurrentDirection() != UP) {
                setCurrentDirection(DOWN);
            }
        }
    }

    /**
    The method itemsCheck checks for collision between the snake and the items in the game and updates the snake and items accordingly.
    @param list A list of items in the game.
    @param gw The game window where the snake and items are drawn.
    */
    public void itemsCheck(List<Items> list, GameWindow gw) {
        int i = -999;
        for (Items item : list) {
            if ((getSnakeHead().getX() == item.getItemX()) && (getSnakeHead().getY() == item.getItemY())) {
                item.wasEaten(this, gw);
                i = list.indexOf(item);
            }
        }
        if (i > -999)
            itemsUpdate(list, i);
        if (getSnakeHead().getX() < 0 || getSnakeHead().getY() < 0 || getSnakeHead().getX() > 20
                || getSnakeHead().getY() > 13) {
            gw.gameOver();
        }
    }
    
    /**
    The method moves the snake in the current direction.
    */
    public void move() {
        switch (getCurrentDirection()) {
            case RIGHT:
                moveRight();
                break;
            case LEFT:
                moveLeft();
                break;
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
        }
    }
    /**
    The method itemsUpdate updates the list of items in the game after the collision with the snake.
    @param List is a list of items in the game.
    @param i The index of the item that was eaten by the snake in the list.
    */
    public void itemsUpdate(List<Items> list, int i) {
        list.remove(i);
    }

    public void moveRight() {
        snakeHead.x = snakeHead.x + 1;
    }

    public void moveLeft() {
        snakeHead.x = snakeHead.x - 1;
    }

    public void moveUp() {
        snakeHead.y = snakeHead.y - 1;
    }

    public void moveDown() {
        snakeHead.y = snakeHead.y + 1;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Point> getSnakeBody() {
        return snakeBody;
    }

    public void setSnakeBody(List<Point> snakeBody) {
        this.snakeBody = snakeBody;
    }

    public int getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(int currentDirection) {
        this.currentDirection = currentDirection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getSnakeHead() {
        return snakeHead;
    }

    public void setSnakeHead(Point snakeHead) {
        this.snakeHead = snakeHead;
    }
}

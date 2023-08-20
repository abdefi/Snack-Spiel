/**

The abstract class Items represents an item that can be drawn on the game window and can be eaten by the snake.
@author Pavel
@version 1.0
*/
package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import snappleGUI.GameWindow;

public abstract class Items {

    protected Image itemImage;
    private int itemX;
    private int itemY;
    private boolean wasEaten = false;

    /**
     * Constructor for the Items class.
     * 
     * @param gw The game window where the item will be added to List of Items.
     * @param gc The graphics context used to draw the item on the game window.
     */
    public Items(GameWindow gw, GraphicsContext gc) {
        itemX = (int) (Math.random() * gw.getRows());
        itemY = (int) (Math.random() * gw.getColumns());
        gw.getItems().add(this);
    }

    /**
     * Draws the item on field.
     * 
     * @param gc The graphics context used to draw the item on the game window.
     */
    public void drawItem(GraphicsContext gc) {
        gc.drawImage(itemImage, itemX * GameWindow.getSquareSize(), itemY * GameWindow.getSquareSize(), GameWindow.getSquareSize(), GameWindow.getSquareSize());
    }

    /**
     * Abstract method that is called when the snake eats the item.
     * 
     * @param sn The snake that eats the item.
     * @param gw The game window where the item was drawn.
     */
    public abstract void wasEaten(Snake sn, GameWindow gw);

    public int getItemX() {
        return itemX;
    }

    public void setItemX(int itemX) {
        this.itemX = itemX;
    }

    public int getItemY() {
        return itemY;
    }

    public void setItemY(int itemY) {
        this.itemY = itemY;
    }

    public boolean isWasEaten() {
        return wasEaten;
    }

    public void setWasEaten(boolean wasEaten) {
        this.wasEaten = wasEaten;
    }

}

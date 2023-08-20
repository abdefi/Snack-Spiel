package test;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import application.Snake;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

class SnakeTest {
	
    private final int RIGHT = 0;
    private final int LEFT = 1;
    private final int UP = 2;
    private final int DOWN = 3;

	@Test
    public void testSnakeGeneration() {
        Snake snake = new Snake();
        snake.snakeGeneration();
        assertEquals(3, snake.getSnakeBody().size());
        assertNotNull(snake.getSnakeHead());
    }
	
    @Test
    public void testHandleD() {
        Snake snake = new Snake();
        snake.handle(new KeyEvent(null, null, null, KeyCode.D, false, false, false, false));
        assertEquals(0, snake.getCurrentDirection());
    }
    
    @Test
    public void testHandleS() {
        Snake snake = new Snake();
        snake.handle(new KeyEvent(null, null, null, KeyCode.S, false, false, false, false));
        assertEquals(3, snake.getCurrentDirection());
    }
    @Test
    public void testHandleA() {
        Snake snake = new Snake();
        snake.handle(new KeyEvent(null, null, null, KeyCode.A, false, false, false, false));
        assertEquals(0, snake.getCurrentDirection());
    }
    @Test
    public void testHandleW() {
        Snake snake = new Snake();
        snake.handle(new KeyEvent(null, null, null, KeyCode.W, false, false, false, false));
        assertEquals(2, snake.getCurrentDirection());
    }
    
        @Test
        public void testMove() {
            Snake snake = new Snake();
            snake.setCurrentDirection(RIGHT);
            snake.setSnakeHead(new Point(0, 0));
            snake.snakeGeneration();
            snake.move();
            snake.move();
            snake.move();
            snake.move();
            snake.move();
            assertEquals(5, snake.getSnakeHead().getX());
            assertEquals(0, snake.getSnakeHead().getY());
        }
        
        @Test
        public void testMove2() {
            Snake snake = new Snake();
            snake.setCurrentDirection(LEFT);
            snake.setSnakeHead(new Point(0, 0));
            snake.snakeGeneration();
            snake.move();
            snake.move();
            snake.move();
            snake.move();
            assertEquals(0, snake.getSnakeHead().getY());
        }
        
        @Test
        public void testMove3() {
            Snake snake = new Snake();
            snake.setCurrentDirection(RIGHT);
            snake.setSnakeHead(new Point(0, 0));
            snake.snakeGeneration();
            snake.move();
            snake.setCurrentDirection(DOWN);
            snake.move();
            snake.setCurrentDirection(LEFT);
            snake.move();
            snake.setCurrentDirection(UP);
            snake.move();
            assertEquals(0, snake.getSnakeHead().getX());
            assertEquals(0, snake.getSnakeHead().getY());
        }
        
        @Test
        public void testMove4() {
            Snake snake = new Snake();
            snake.setCurrentDirection(10);
            snake.setSnakeHead(new Point(0, 0));
            snake.snakeGeneration();
            snake.move();
            assertEquals(0, snake.getSnakeHead().getX());
            assertEquals(0, snake.getSnakeHead().getY());
        }

}

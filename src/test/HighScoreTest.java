package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.HighScore;

class HighScoreTest {

    @Test
    public void testGetName() {
        HighScore highScore = new HighScore("Test", 100);
        assertEquals("Test", highScore.getName());
    }

    @Test
    public void testSetName() {
        HighScore highScore = new HighScore("Test", 100);
        highScore.setName("Test");
        assertEquals("Test", highScore.getName());
    }

    @Test
    public void testNameProperty() {
        HighScore highScore = new HighScore("Test", 100);
        assertNotNull(highScore.nameProperty());
    }

    @Test
    public void testGetScore() {
        HighScore highScore = new HighScore("Test", 100);
        assertEquals(100, highScore.getScore());
    }

    @Test
    public void testSetScore() {
        HighScore highScore = new HighScore("Test", 100);
        highScore.setScore(200);
        assertEquals(200, highScore.getScore());
    }

    @Test
    public void testScoreProperty() {
        HighScore highScore = new HighScore("Test", 100);
        assertNotNull(highScore.scoreProperty());
    }
}



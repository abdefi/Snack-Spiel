package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import application.HighScore;
import application.HighScoreTable;
import javafx.scene.control.TableColumn;

public class HighScoreTableTest {
	@Test
	public void testGetItems() {
		// Create a new HighScoreTable
		HighScoreTable table = new HighScoreTable();

		// Check that the table's items list is empty
		assertTrue(table.getItems().isEmpty());

		// Add a HighScore to the table's items list
		HighScore highScore = new HighScore("Alice", 100);
		table.getItems().add(highScore);

		// Check that the table's items list is not empty
		assertFalse(table.getItems().isEmpty());
	}

	@Test
	public void testGetColumns() {
		// Create a new HighScoreTable
		HighScoreTable table = new HighScoreTable();

		// Get the table's columns
		List<TableColumn<HighScore, ?>> columns = table.getColumns();

		// Check that the table has two columns
		assertEquals(2, columns.size());

		// Check that the first column is the name column
		assertEquals("Name", columns.get(0).getText());

		// Check that the second column is the score column
		assertEquals("Score", columns.get(1).getText());
	}
}
package bbc.codingtests.gameoflife.gamestate;

import org.junit.Test;

import bbc.codingtests.gameoflife.exceptions.InvalidInputException;
import static org.junit.Assert.*;

public class GameStateTest {

	// Tests that a game state grid converted to a String is equal to the input String
    @Test
    public void testStringGridRetrieval() throws InvalidInputException {
        String input = "...\n***\n...";
        GameState testState = new GameStateImpl(input);
        assertEquals(input, testState.toString());
    }
    
    // Tests that a specified cell results in the expected alive state based on evaluating the number of neighbours
    @Test
    public void testCellNeighbourEvaluation() throws InvalidInputException {
        String input = ".*.\n*.*\n...";
        GameState testState = new GameStateImpl(input);
        assertTrue("Row 1, col 1 should be alive in the next iteration", testState.evaluateNeighbours(1, 1));
    }
	
    // Tests that the game state input String results in the expected grid
    @Test
    public void testParsing() throws InvalidInputException {
        String input = ".*.\n*.*\n...";
        GameState testState = new GameStateImpl(input);
        assertTrue("Row 0, col 1 should be alive", testState.isCellAliveAt(0,1));
        assertFalse("Row 2, col 2 should not be alive", testState.isCellAliveAt(2,2));
    }
    
    // Tests that the game state input String results in a grid with the expected dimensions
    @Test
    public void testRowColCounts() throws InvalidInputException {
        String input = "...\n***\n..*";
        GameState testState = new GameStateImpl(input);
        assertEquals("The game should have 3 columns", 3, testState.getCols());
        assertEquals("The game should have 3 rows", 3, testState.getRows());
    }
    
    // Tests that the game state input String results in a grid with the expected dimensions for a different number of columns then rows
    @Test
    public void testRowColCountsDifferentDimensions() throws InvalidInputException {
        String input = "....\n****\n..**";
        GameState testState = new GameStateImpl(input);
        assertEquals("The game should have 4 columns", 4, testState.getCols());
        assertEquals("The game should have 3 rows", 3, testState.getRows());
    }
        
}

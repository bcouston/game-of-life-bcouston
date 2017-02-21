package bbc.codingtests.gameoflife;

import bbc.codingtests.gameoflife.exceptions.InvalidInputException;
import bbc.codingtests.gameoflife.gamestate.GameState;
import bbc.codingtests.gameoflife.gamestate.GameStateImpl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class LifeTest {
	
	/* NOTE: To see these tests rendered in a window, set the LifeImpl parameter
	 * inputs to the size of the input grid. The glider test (6,6) is a good
	 * demonstration of the program.*/
	
	// Exception to be expected with an invalid game state input String 
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	// Tests Scenario 0 and 5 of of a grid with no live cells
	@Test
	public void testEmptyGrid() throws InvalidInputException {
		String emptyStateInput = "...\n...\n...";
		Life testLife = new LifeImpl(-1,-1);
		GameState emptyState = new GameStateImpl(emptyStateInput);
		assertEquals("An empty grid should stay the same", emptyStateInput, testLife.evolveIterate(emptyState, 1).toString());
	}
	
	// Tests Scenario 1 with an underpopulated grid of a live single cell with no neighbours
	@Test
	public void testUnderPopulatedGridNoNeighbours() throws InvalidInputException {
		String underPopulatedStateInput = "*..\n...\n...";
		String emptyStateOutput = "...\n...\n...";
		Life testLife = new LifeImpl(-1,-1);
		GameState underPopulatedState = new GameStateImpl(underPopulatedStateInput);
		assertEquals("A grid with a live cell with no neighbours should cause the cell to die", emptyStateOutput , testLife.evolveIterate(underPopulatedState, 1).toString());
	}
	
	// Tests Scenario 1 with an underpopulated grid of a live cell with a single neighbour
	@Test
	public void testUnderPopulatedGridSingleNeighbour() throws InvalidInputException {
		String underPopulatedStateInput = "**.\n...\n...";
		String emptyStateOutput = "...\n...\n...";
		Life testLife = new LifeImpl(-1,-1);
		GameState underPopulatedState = new GameStateImpl(underPopulatedStateInput);
		assertEquals("A grid with a live cell with a single neighbour should cause the cell to die", emptyStateOutput , testLife.evolveIterate(underPopulatedState, 1).toString());
	}
	
	// Tests Scenario 2 with an overpopulated grid of a live cell with 4 neighbours
	@Test
	public void testOverPopulatedGridMinimum() throws InvalidInputException {
		String overPopulatedStateInput = ".*.\n***\n.*.";
		String nextStateOutput = "***\n*.*\n***";
		Life testLife = new LifeImpl(-1,-1);
		GameState overPopulatedState = new GameStateImpl(overPopulatedStateInput);
		assertEquals("A grid with a live cell with 4 neighbours should cause the cells to die", nextStateOutput , testLife.evolveIterate(overPopulatedState, 1).toString());
	}
	
	// Tests Scenario 2 with an overpopulated grid of live cells with more than 4 neighbours
	@Test
	public void testOverPopulatedGridMaximum() throws InvalidInputException {
		String overPopulatedStateInput = "***\n***\n***";
		String nextStateOutput = "*.*\n...\n*.*";
		Life testLife = new LifeImpl(-1,-1);
		GameState overPopulatedState = new GameStateImpl(overPopulatedStateInput);
		assertEquals("A grid with cells with more than 4 neighbours should cause the cells to die", nextStateOutput , testLife.evolveIterate(overPopulatedState, 1).toString());
	}
	
	// Tests Scenario 3 with a grid of a live cell with 2 neighbours
	@Test
	public void testCellSurvivalTwoNeighbours() throws InvalidInputException {
		String cellSurvivalStateInput = "**.\n*..\n...";
		String nextStateOutput = "**.\n**.\n...";
		Life testLife = new LifeImpl(-1,-1);
		GameState cellSurvivalState = new GameStateImpl(cellSurvivalStateInput);
		assertEquals("A grid with a live cell with 2 neighbours should survive to the next iteration", nextStateOutput , testLife.evolveIterate(cellSurvivalState, 1).toString());
	}
	
	// Tests Scenario 3 with a grid of a live cell with 3 neighbours
	@Test
	public void testCellSurvivalThreeNeighbours() throws InvalidInputException {
		String cellSurvivalStateInput = "**.\n**.\n...";
		String nextStateOutput = "**.\n**.\n...";
		Life testLife = new LifeImpl(-1,-1);
		GameState cellSurvivalState = new GameStateImpl(cellSurvivalStateInput);
		assertEquals("A grid with a live cell with 3 neighbours should survive to the next iteration", nextStateOutput , testLife.evolveIterate(cellSurvivalState, 1).toString());
	}
	// Tests Scenario 4 with a grid of a dead cell with 3 neighbours
	@Test
	public void testCreationOfCellLife() throws InvalidInputException {
		String creationOfCellLifeStateInput = "***\n...\n...";
		String nextStateOutput = ".*.\n.*.\n...";
		Life testLife = new LifeImpl(-1,-1);
		GameState creationOfCellLifeState = new GameStateImpl(creationOfCellLifeStateInput);
		assertEquals("A grid with a dead cell with 3 neighbours should become live in the next iteration", nextStateOutput , testLife.evolveIterate(creationOfCellLifeState, 1).toString());
	}
	
	// Tests Scenario 4 with a grid of a dead cell with less than 3 neighbours 
	@Test
	public void testUnderPopulatedCreationOfCellLife() throws InvalidInputException {
		String underPopulatedCreationOfCellLifeStateInput = "...\n*.*\n...";
		String nextStateOutput = "...\n...\n...";
		Life testLife = new LifeImpl(-1,-1);
		GameState underPopulatedCreationOfCellLifeState = new GameStateImpl(underPopulatedCreationOfCellLifeStateInput);
		assertEquals("A grid with a dead cell with less than 3 neighbours should stay dead in the next iteration", nextStateOutput , testLife.evolveIterate(underPopulatedCreationOfCellLifeState, 1).toString());
	}
	
	// Tests Scenario 4 with a grid of a dead cell with more than 3 neighbours 
	@Test
	public void testOverPopulatedCreationOfCellLife() throws InvalidInputException {
		String overPopulatedCreationOfCellLifeStateInput = ".*.\n*.*\n.*.";
		String nextStateOutput = ".*.\n*.*\n.*.";
		Life testLife = new LifeImpl(-1,-1);
		GameState overPopulatedCreationOfCellLifeState = new GameStateImpl(overPopulatedCreationOfCellLifeStateInput);
		assertEquals("A grid with a dead cell with more than 3 neighbours should stay dead in the next iteration", nextStateOutput , testLife.evolveIterate(overPopulatedCreationOfCellLifeState, 1).toString());
	}
	
	// Tests Scenario 6 with the specified input and expecting the specified output for 2 steps
	@Test
	public void testScenario6Input() throws InvalidInputException {
		String scenario6StateInput = "...\n***\n...";
		String nextStateOutput = ".*.\n.*.\n.*.";
		Life testLife = new LifeImpl(-1,-1);
		GameState scenario6NextState = testLife.evolveIterate(new GameStateImpl(scenario6StateInput), 1);
		assertEquals("A grid with initial cells equal to the initial state of Scenario 6 should be equal to the next state specified in Scenario 6 in the next iteration", nextStateOutput , scenario6NextState.toString());
		assertEquals("A grid with initial cells equal to the next state of Scenario 6 should be equal to the initial state specified in Scenario 6 in the next iteration", scenario6StateInput , testLife.evolveIterate(scenario6NextState, 1).toString());
	}
	
	// Tests the glider cell collection behaves as expected in a traditional Game of Life
	@Test
	public void testGliderServeralIterations() throws InvalidInputException {
		String gliderStateInput = ".*....\n..*...\n***...\n......\n......\n......";
		String gliderStateOutput = "......\n......\n......\n....*.\n..*.*.\n...**.";
		Life testLife = new LifeImpl(-1,-1);
		GameState gliderState = new GameStateImpl(gliderStateInput);
		assertEquals("A grid initialised with the glider cell collection will produce the predicted output after 10 iterations", gliderStateOutput , testLife.evolveIterate(gliderState, 10).toString());
	}
	
	// Tests that an exception is raised for an invalid game state input String with unequal amounts of columns in each row
	@Test
	public void testInvalidNoOfColsInput() throws InvalidInputException {
		String invalidInput = ".*.\n.**.\n.*.";
		Life testLife = new LifeImpl(-1,-1);
		exception.expect(InvalidInputException.class);
		exception.expectMessage("Input string does not constitute a square grid. Please check that all rows have the same number of cells.");
		GameState invalidInputState = new GameStateImpl(invalidInput);
	}
	
	// Tests that an exception is raised for an invalid game state input String with invalid characters (not '*', '.', '\n')
	@Test
	public void testInvalidCharactersInput() throws InvalidInputException {
		String invalidInput = ".*.\n.e.\n.*.";
		Life testLife = new LifeImpl(-1,-1);
		exception.expect(InvalidInputException.class);
		exception.expectMessage("e character is not parsable as a cell.");
		GameState invalidInputState = new GameStateImpl(invalidInput);
	}

}

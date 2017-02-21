package bbc.codingtests.gameoflife.gamestate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bbc.codingtests.gameoflife.exceptions.InvalidInputException;
import bbc.codingtests.gameoflife.gamecell.GameCell;

public class GameStateImpl implements GameState {
	
	// 2D ArrayList of the grid of cells for the current state of the game
	private List<List<GameCell>> grid = new ArrayList<List<GameCell>>();
	// Number of rows in grid
	private int rowCount = 0;
	// Number of columns in grid
	private int colCount = 0;

	/**
	 * @return Converts the 2D ArrayList of cells into a human-readable String
	 * in the same format as the initial String input
	 */
    @Override
    public String toString() {
    	String gridString= "";
    	for (int r = 0; r < grid.size(); r++) {
    		for (int c = 0; c < grid.get(r).size(); c++) {
    			if (grid.get(r).get(c).getAliveState()) {
    				gridString += "*";
    			} else if (!grid.get(r).get(c).getAliveState()) {
    				gridString += ".";
    			}
    		}
    		if (r != grid.size() - 1) {
    			gridString += "\n";
    		}
    	}
    	return gridString;
    }

    /**
     * Constructor that initialises the game grid with dead or alive cells as well as
     * it's row and column dimensions determined by parsing the String input.
     * @param input String input representation of the initial game grid with '*'
     * 	indicating an alive cell, '.' a dead cell and '\n' a new row.
     * @throws InvalidInputException Exception that halts the running of the program
     * 	if the input is not in the format specified above.
     */
    public GameStateImpl(String input) throws InvalidInputException {
    	String[] gridRows = input.split("\n");
    	rowCount = gridRows.length;
    	for (int r = 0; r < rowCount; r++) {
    		grid.add(new ArrayList<GameCell>());
    		char[] gridRow = gridRows[r].toCharArray();
    		if (r == 0) {
    			colCount = gridRow.length;
    		} else if (gridRow.length != colCount) {
    			// Number of columns per row is not consistent, therefore throw an Exception
    			throw new InvalidInputException("Input string does not constitute a square grid. Please check that all rows have the same number of cells.");
    		}
    		for (int c = 0; c < colCount; c++) {
    			switch (gridRow[c]) {
    				// If cell is denoted as alive
    				case '*':
    					grid.get(r).add(new GameCell(true));
    					break;
    				// If cell is denoted as dead
    				case '.':
    					grid.get(r).add(new GameCell(false));
    					break;
    				// If cell is none of expected input characters - throw Exception
    				default:
    					throw new InvalidInputException(gridRow[c] + " character is not parsable as a cell.");
    			}
    		}
    	}
    }
    
    /**
     * {@inheritDoc}
     */
	public boolean evaluateNeighbours(int cellRow, int cellCol) {
		List<Boolean> neighbourStates = new ArrayList<Boolean>();
		boolean currentCellState = grid.get(cellRow).get(cellCol).getAliveState();
		// Gather alive states of all neighbouring cells
		for (int r = cellRow - 1; r <= cellRow + 1; r++) {
			for (int c = cellCol - 1; c <= cellCol + 1; c++) {
				if ((r >= 0 && r < rowCount) && (c >= 0 && c < colCount)) {
					if ((r != cellRow) || (c != cellCol)) {
						neighbourStates.add(grid.get(r).get(c).getAliveState());
					}
				}
			}
		}
		return evaluateCellState(neighbourStates, currentCellState);
	}

	/**
	 * {@inheritDoc}
	 */
    public boolean isCellAliveAt(int row, int col) {
    	return grid.get(row).get(col).getAliveState();
    }
    
    /**
     * {@inheritDoc}
     */
    public List<List<GameCell>> getGrid() {
    	return grid;
    }
    
    /**
     * {@inheritDoc}
     */
    public int getRows() {
    	return rowCount;
    }

    /**
     * {@inheritDoc}
     */
    public int getCols() {
    	return colCount;
    }
    
    /**
     * Returns whether a cell will be alive or dead in the next iteration based on the states
     * of it's neighbouring cells and according to the rules of the Game of Life
     * @param neighbourStates a Boolean List of whether each of the cell's neighbours
     * 	(vertically, horizontally and diagonally) are alive or not.
     * @param currentCellState a boolean value representing whether the cell of focus is
     * 	alive or not in the current iteration
     * @return a boolean value representing whether the cell of focus will be alive or not
     * 	in the next iteration
     */
	private boolean evaluateCellState(List<Boolean> neighbourStates, boolean currentCellState) {
		int noOfAliveNeighbourCells = Collections.frequency(neighbourStates, true);
		// If <2 neighbour cells are alive, cell will be dead
		if (noOfAliveNeighbourCells < 2) {
			return false;
		// If 2 neighbour cells are alive and cell is alive in current state, cell will be alive
		} else if ((noOfAliveNeighbourCells == 2) && (currentCellState)) {
			return true;
		// If 3 neighbour cells are alive, cell will be alive
		} else if (noOfAliveNeighbourCells == 3) {
			return true;
		// If >3 neighbour cells are alive, cell will be dead
		} else if (noOfAliveNeighbourCells > 3) {
			return false;
		} else {return false;}
	}
	
}

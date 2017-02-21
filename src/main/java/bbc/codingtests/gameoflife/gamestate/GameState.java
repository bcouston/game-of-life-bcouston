package bbc.codingtests.gameoflife.gamestate;

import java.util.List;

import bbc.codingtests.gameoflife.gamecell.GameCell;

public interface GameState {
	
    /**
     * Whether the cell at the given row and column will be alive
     * or dead in the next iteration based on the states of it's
     * neighbouring cells.
     * @param cellRow integer specifying the row of the game grid
     * 	to locate the cell
     * @param cellCol integer specifying the column of the game grid
     * 	to locate the cell
     * @return a boolean value representing whether the cell at the
     * 	row, column position in the game grid will be alive in the
     * 	next iteration, according to the Game of Life rules
     */
	boolean evaluateNeighbours(int cellRow, int cellCol);
	
    /**
     * Whether the cell at the given row and column is alive or not
     * Should return false if the coordinates are outside the grid
     * @param row the row of the game grid to locate the cell
     * @param col the column of the game grid to locate the cell
     * @return a boolean value representing whether the cell at the
     * 	row, column position in the game grid is alive
     */
    boolean isCellAliveAt(int row, int col);
    
    /**
     * @return a 2D ArrayList of cells in the current state of
     * the game grid
     */
    List<List<GameCell>> getGrid();
    
    /**
     * @return Number of rows the game has
     */
    int getRows();

    /**
     * @return Number of columns the game has
     */
    int getCols();
    
}

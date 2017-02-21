package bbc.codingtests.gameoflife;

import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import bbc.codingtests.gameoflife.gamecell.GameCell;
import bbc.codingtests.gameoflife.gamestate.GameState;
import bbc.codingtests.gameoflife.graphics.RenderGrid;

public class LifeImpl implements Life
{
	
	// Whether the grid should render in a seperate window for each iteration
	private boolean shouldGridRender = true;
	// Grid render components
	private static RenderGrid gridRender = new RenderGrid(1024,768);
	private static JFrame frame = new JFrame();
	
	/**
	 * Constructor that confirms a new Game of Life has started
	 * and determines whether to render the grid in a seperate
	 * window based on a parameters.
	 * @param gridRows the number of rows in the game state grid
	 * @param gridCols the number of columns in the game state grid
	 */
	public LifeImpl(int gridRows, int gridCols) {
		System.out.println("New Game of Life\n");
		if ((gridRows != -1) && (gridCols != -1)) {
			initGridRender(gridRows, gridCols);
		} else {
			shouldGridRender = false;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public GameState evolveIterate(GameState currentState, int iterations) {
        renderGridIteration(currentState);
		for (int n = 0; n < iterations; n++) {
			currentState = evolve(currentState);
			// Print current state grid to Console
			System.out.println("Iteration " + (n + 1) + ":\n" + currentState.toString() + "\n");
			if (shouldGridRender) {
				/* Pause for one second after each iteration so
				 * evolution is clearly visible. Time to pause
				 * welcome to change according to user's needs. */
		        try {
		        	Thread.currentThread().sleep(1000);
		        } catch (InterruptedException e) {
		        	e.printStackTrace();
			    }
		        // Render next iteration in window
		        renderGridIteration(currentState);
			}
		}
		return currentState;
	}
	
	/**
	 * Produce the next game state from the current game state according
	 * to the rules of the Game of Life.
	 * @param currentState the current game state to evolve
	 * @return the next game state
	 */
	private GameState evolve(GameState currentState) {
		List<List<GameCell>> grid = currentState.getGrid();
		List<List<Boolean>> nextGridState = new ArrayList<List<Boolean>>();   
		for (int r = 0; r < grid.size(); r++) {
			nextGridState.add(new ArrayList<Boolean>());
			for (int c = 0; c < grid.get(r).size(); c++) {
				nextGridState.get(r).add(currentState.evaluateNeighbours(r, c));	
			}
		}
		for (int r = 0; r < grid.size(); r++) {
			for (int c = 0; c < grid.get(r).size(); c++) {
				grid.get(r).get(c).setAliveState(nextGridState.get(r).get(c));
			}
		}
		return currentState;
	}
	
	/**
	 * Initialise the elemental components of the rendered grid window
	 * and link the internal representation of the grid to the front-end
	 * appearance.
	 * @param gridRows the number of rows in the game state grid
	 * @param gridCols the number of columns in the game state grid
	 */
	private void initGridRender(int gridRows, int gridCols) {
		// Initialise elemental components of JFrame
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(1024,768);
	    frame.setTitle("John Conway's Game Of Life");
	    frame.setVisible(true);
	    // Add grid image to JFrame
	    frame.getContentPane().add(new JLabel(new ImageIcon(gridRender.getGridImage())));
		// Compute insets to determine appropriate size for frame considering grid
	    Insets insets = frame.getInsets();
	    int insetwidth = insets.left + insets.right;
	    int insetheight = insets.top + insets.bottom;
	    // Determine rendered grid dimensions
	    gridRender.setBricks(gridRows, gridCols); 
	    // Determine size of rendered "bricks" representing cells
	    int[] brickSize = gridRender.getBricks();  
	    // Set JFrame to more appropriate sized based on brick size
	    frame.setSize(((brickSize[0]*gridRows)+insetwidth),((brickSize[1]*gridCols)+insetheight));
	}
	
	/**
	 * Render the grid of the current game state.
	 * @param currentState the current game state.
	 */
	private void renderGridIteration(GameState currentState) {
		gridRender.blank();
		gridRender.drawCells(currentState.getGrid());
		frame.getContentPane().repaint();
	}
	
}

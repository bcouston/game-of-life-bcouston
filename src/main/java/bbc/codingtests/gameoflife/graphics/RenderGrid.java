package bbc.codingtests.gameoflife.graphics;

import bbc.codingtests.gameoflife.gamecell.GameCell;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;


public class RenderGrid {
	
	// Image of the grid render
	private BufferedImage gridImage;
	// x-size of image
	private int xDim=100;
	// y-size of image
	private int yDim=100;
	// Size of drawn cells (bricks)
	private int[] brickSize;
	// Dimensions of the grid
	private int[] gridDims;

	/**
	 * Constructor that initialises the dimensions of rendered grid window
	 * and dimensions of the bricks.
	 * @param windowX the x dimension of the window
	 * @param windowY the y dimension of the window
	 */
	public RenderGrid(int windowX, int windowY) {
		xDim = windowX;
		yDim = windowY;
		brickSize = new int[2];
		gridDims = new int[2];
		gridDims[0] = 0;
		gridDims[1]= 0;
		gridImage = new BufferedImage(xDim,yDim,BufferedImage.TYPE_INT_RGB);
	}

	/**
	 * Fill the rendered grid with white live cells according to the current state
	 * of the game grid.
	 * @param grid the current state of game grid storing a collection of cells
	 */
	public void drawCells(List<List<GameCell>> grid){
		Graphics g = gridImage.getGraphics();
		for (int r = 0; r < grid.size(); r++){
			for(int c =0; c < grid.get(r).size(); c++){
				if (grid.get(r).get(c).getAliveState() == true) {
					g.fill3DRect(((c*brickSize[0])),((r*brickSize[1])),(brickSize[0]),(brickSize[1]),true);
				}
			}
		}
	}  
	
	/**
	 * Clear the rendered grid image so rendered alive
	 * cells do not persist in the next iteration.
	 */
	public void blank() {                             
		Graphics g = gridImage.getGraphics();
		g.clearRect(0,0,xDim,yDim);
	}
	
	/**
	 * @return the Image representing the current state
	 * of the game grid.
	 */
	public Image getGridImage() {
		return (gridImage);
	} 
	
	/**
	 * Sets the dimensions of the bricks according to the
	 * number of cells in the game grid.
	 * @param xCells the number of cells per row
	 * @param yCells the number of cells per column
	 */
	public void setBricks(int xCells, int yCells){
		gridDims[0] = xCells;
		gridDims[1] = yCells;
		brickSize[0] = (int)(xDim/xCells);                  
		brickSize[1] = (int)(yDim/yCells);
	}
  
	/**
	 * @return the {x,y} dimensions of the bricks.
	 */
	public int[] getBricks(){ 
		return(brickSize);
	}
	
}

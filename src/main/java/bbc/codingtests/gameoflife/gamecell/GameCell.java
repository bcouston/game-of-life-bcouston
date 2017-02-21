package bbc.codingtests.gameoflife.gamecell;

public class GameCell {
	
	// Whether the cell is alive
	private boolean aliveState = false;
  
	/**
	 * Constructor that sets the alive state of the cell.
	 * @param isAlive whether the cell is alive
	 */
	public GameCell(boolean isAlive) {
		aliveState = isAlive;  
	}
  
	/**
	 * Sets the alive state of the cell.
	 * @param isAlive whether the cell is alive
	 */
	public void setAliveState(boolean isAlive) {
		aliveState = isAlive;
	}
  
	/**
	 * @return the alive state of the cell.
	 */
	public boolean getAliveState(){
		return aliveState;
	}
	
}

package bbc.codingtests.gameoflife;

import bbc.codingtests.gameoflife.gamestate.GameState;

public interface Life
{

	/**
	 * Apply specified number of iterations of the Game of Life to the
	 * given state and return the resulting state.
	 * @param currentState the current state of the game to evolve
	 * to the next iteration according to the Game of Life rules
	 * @param iterations the number of times to evolve the state
	 * until a final state is returned
	 * @return the final state of the game
	 */
	GameState evolveIterate(GameState currentState, int iterations);
	
}

package bbc.codingtests.gameoflife.exceptions;

public class InvalidInputException extends Exception {
	
	/**
	 * Constructor for invalid input exception when user submits an
	 * invalid seed String as the initialiser for the game state
	 * @param message the error message to display to the user in
	 * 	the Console.
	 */
	public InvalidInputException(String message) {
		super(message);
	}
	
}

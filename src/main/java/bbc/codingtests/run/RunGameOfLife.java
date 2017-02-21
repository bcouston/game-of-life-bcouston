package bbc.codingtests.run;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import bbc.codingtests.gameoflife.Life;
import bbc.codingtests.gameoflife.LifeImpl;
import bbc.codingtests.gameoflife.exceptions.InvalidInputException;
import bbc.codingtests.gameoflife.gamestate.GameState;
import bbc.codingtests.gameoflife.gamestate.GameStateImpl;

/* --------------------------------------------------------------------------------
 * Created by Ben Couston - Submitted on 21/02/2017 as part of the BBC Coding Test.
 * --------------------------------------------------------------------------------
 */

public class RunGameOfLife {
	
	/*
	 * ------ CMD ARGUMENTS ------
	 * Option 1 - No arguments
	 * Program is ran with the format of Scenario 6 provided in the Exercise description.
	 * All game states printed in the Console.
	 * Visual rendering of the Game of Life is also provided in a seperate window.
	 * 
	 * Option 2 - read {file_path} - e.g. read Scenario6.txt
	 * Program reads in file with filepath provided based from the working directory.
	 * File contents are read in as the String input seed rather than the default.
	 * Note: An input text file must have rows seperated by actual
	 * line-breaks rather than line-break characters ("\n") to be
	 * eligible. See example in Scenario 6.txt.
	 * 
	 * Option 3 - {blank||Option 2} norender - e.g. norender OR read Scenario6.txt norender
	 * Program does not visually render the Game of Life in a seperate window.
	 */
	
	// Number of iterations of the Game of Life to run - can be changed to suit needs
	private static final int ITERATIONS = 2;
	
	// Run the Game of Life
	public static void main(String[] argv) throws InvalidInputException {
		// Input String seed for game state (currently in Scenario 6 format, can be altered)
		String seed = "...\n***\n...";
		// Read in text file with input String if user provides arguments
		if (argv.length > 0) {
			if ((argv[0].equals("read")) && (argv.length >= 2) && (argv.length <= 3)) {
				seed = parseTxtFile(argv[1]);
			}
		}
        System.out.println("Seed String:\n" + seed);
        // Initialise game state with seed
		GameState initialGameState = new GameStateImpl(seed);
		int gridRows = initialGameState.getRows();
		int gridCols = initialGameState.getCols();
		/* Set program to not visually render the game state grid in a seperate
		 * window if the user provides the argument "norender" */
		if (((argv.length == 3) && (argv[2].equals("norender"))) || ((argv.length == 1) && (argv[0].equals("norender")))) {
			System.out.println("No grid rendering");
			// -1 indicates that the grid should not render during iterations
			gridRows = -1;
			gridCols = -1;
		}
		Life gameOfLife = new LifeImpl(gridRows, gridCols);
		// Print initial state grid to Console
		System.out.println("Initial game state:\n" + initialGameState.toString() + "\n");
		// Iterate evolutions
		gameOfLife.evolveIterate(initialGameState, ITERATIONS);
	}
	
	/**
	 * Given the user has provided a file name as an argument,
	 * read a text file in from the working directory as a
	 * String input to initialise the game state.
	 * @param filePath the String file path of the input file
	 * @return
	 */
	/* Note: An input text file must have rows seperated by actual
	 * line-breaks rather than line-break characters ("\n") to be
	 * eligible. See example in Scenario 6.txt.*/
	private static String parseTxtFile(String filePath) {
		System.out.println("Reading from file...");
		String seedFromFile = new String();
		String thisLine = null;
		// Read lines from input file
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
			while ((thisLine = fileReader.readLine()) != null) {
				seedFromFile += thisLine;
				seedFromFile += "\n";
	         }
			fileReader.close();
		// If error reading file
		} catch (IOException e) {
			System.err.println("Cannot read from text file provided. Program has quit.");
			System.err.println(e);
			System.exit(0);
		}
		// Remove trailing line break
		seedFromFile = seedFromFile.substring(0, seedFromFile.length() - 1);
		System.out.println("Success!");
		return seedFromFile;
	}
	
}

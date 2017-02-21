## Instructions ##

Submitted by Ben Couston as part of the BBC Coding Test.

Program made in Java using Eclipse IDE. Loaded as Maven project.

To start the program run RunGameOfLife.java file in gameoflife-bcouston\src\main\java\bbc\codingtests\run.

Program by default demonstrates Scenario 6 specified in the Game of Life Exercise Description.
Different game states can be produced by altering the 'seed' String variable defined in RunGameOfLife.java containing '*' for a live cell, '.' for a dead cell and '\n' for a row break.
Alternatively, a text file input can be provided as long as the text file is located in the working directory ( gameoflife-bcouston\).
An example text file input is provided in Scenario6.txt. Note that unlike a manually inputted seed String, row breaks must be specified by an actual line break in the file.

CMD arguments can be provided to inidicate the desired input of the text file as well as other features. Namely:

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
	
All tests pass with a total of 19 tests spread across LifeTest.java and GameStateTest.java.

Please email if there are any questions.
// package numberguessinggame;

import java.util.Scanner;
import java.util.Arrays;

public class NumberGuessingGame { 

    public static void main(String[] args) {
        /*
         * Declare variables and initialize some variables this program will use
         */
        boolean showDebuggingLogs = false;
        boolean gameInProgress = true;
        int gameCount = 0;
        int maxAmountOfGames = 10000;
        String userInput;
        String[] splitUserInput;
        int min;
        int max;
        boolean validMaxandMin = false;
        int currentGameRandomNumber;
        int currentGuess;
        boolean correct = false;
        int currentGameGuessCount = 0;
        int totalGuessCountForAllGames = 0; // for each game round save how many guesses it took user to guess correctly
        int[] arrayOfGameGuessCounts = new int[maxAmountOfGames]; // used to store how many guesses each round had
        String userPromptForMinAndMaxEntry = "Please enter the min and max values for the number range in integer, q or Q to quit: ";
        Scanner scnr = new Scanner(System.in);

        if (showDebuggingLogs)
            System.out.println("initial arrayOfGameGuessCounts: " + Arrays.toString(arrayOfGameGuessCounts));

        // start a while loop to allow user to continue to play game for multiple rounds
        // unless user quits
        while (gameInProgress) { // Think of this while loop repeating each game round
            /**
             * Prompt to user the initial prompts section of code
             */
            if (gameCount <= 0) { // only display these print statements the first round
                System.out.println("Welcome to Guess a Number Game");
                System.out.println("The system will generate a random # that you will try to guess");
                System.out.println("Enter 'q' to quit if you're done");
                System.out.println("The system will then output the average guesses it took for you");
            }
            System.out.print(userPromptForMinAndMaxEntry); // print this every round

            /**
             * Collect max and min values from user section of code by handling if they quit
             * and validating the user input
             */

            // Save the user's string input containing max and min values seperated by a
            // space
            // scnr.nextLine will read input from the terminal until the enter button is
            // pressed
            userInput = scnr.nextLine(); // example userInput is "1 100" and can be visualized as ["1", " ", "1", "0",
                                         // "0"] (an array of characters, otherwise known as a string)
            if (showDebuggingLogs)
                System.out.println(userInput);

            /*
             * Handle if user decided to quit
             */
            if (userInput.equals("q") || userInput.equals("Q")) {
                if (showDebuggingLogs)
                    System.out.println("In quit block");
                gameInProgress = false;
                break; // continue runtime by executing code after the while (gameInProgress) loop
                       // since the game is not in progress anymore
            }

            /*
             * Convert the user's string input to two integers
             */
            splitUserInput = userInput.split(" "); // userInput.split(" ") returns an array of strings and an example
                                                   // value is ["1", "100"]
                                                   // and can be visualized as [ ["1"], ["1", "0", "0"] ] (an array
                                                   // where every element is a character array no matter the length)
            if (showDebuggingLogs)
                System.out.println(Arrays.toString(splitUserInput));

            // assign the min variable using the splitUserInput's first element
            min = Integer.parseInt(splitUserInput[0]); // min value is the first element in splitUserInput (["1"])
                                                       // converted to a integer 1
            if (showDebuggingLogs)
                System.out.println(min);

            // assign the max variable using the splitUserInput's second element
            max = Integer.parseInt(splitUserInput[1]); // max value is the second element in splitUserInput ("100" or
                                                       // ["1", "0", "0"] for visability sake) converted to a integer 1
            if (showDebuggingLogs)
                System.out.println(max);

            /**
             * User max and min input validation code section
             * Now that we have max and min saved as integers before creating
             * currentGameRandomNumber
             * we validate the min and max values. We do this to consider if we need to
             * prompt to the user to re-enter new input if they are not following our game
             * rules.
             */

            while (validMaxandMin == false) { // loop because user could enter input incorrectly multiple times
                // and if max and min are not valid re-prompt user
                if (min > max) {
                    System.out.println("Min is > max so try again");
                } else if (min < 0 || max < 0) {
                    System.out.println("You used a negative number for the range so try again");
                } else if (min > 10000 || max > 10000) {
                    System.out.println("You exceeded the max value for max (10000) so try again");
                } else {
                    validMaxandMin = true;
                }

                /*
                 * Re-prompt user if the input is not valid and let this while loop testing the
                 * validMaxandMin value run once more
                 */
                if (validMaxandMin == false) {
                    System.out.print(userPromptForMinAndMaxEntry);
                    userInput = scnr.nextLine();
                    /*
                     * Handle if user decided to quit
                     */
                    if (userInput.equals("q") || userInput.equals("Q")) {
                        gameInProgress = false;
                        break; // continue runtime by executing code after the while (gameInProgress) loop
                               // since the game is not in progress anymore
                    }

                    /*
                     * Convert the user's string input to two integers
                     */
                    splitUserInput = userInput.split(" "); // userInput.split(" ") returns an array of strings and an
                                                           // example value is ["1", "100"]
                    // and can be visualized as [ ["1"], ["1", "0", "0"] ] (an array where every
                    // element is a character array no matter the length)
                    if (showDebuggingLogs)
                        System.out.println(Arrays.toString(splitUserInput));

                    // assign the min variable using the splitUserInput's first element
                    min = Integer.parseInt(splitUserInput[0]); // min value is the first element in splitUserInput
                                                               // (["1"])
                    // converted to a integer 1
                    if (showDebuggingLogs)
                        System.out.println(min);

                    // assign the max variable using the splitUserInput's second element
                    max = Integer.parseInt(splitUserInput[1]); // max value is the second element in splitUserInput
                                                               // ("100" or
                    // ["1", "0", "0"] for visability sake) converted to a integer 1
                    if (showDebuggingLogs)
                        System.out.println(max);
                }
            }
            if (gameInProgress == false)
                break; // continue runtime by executing code after the while (gameInProgress) loop
                       // since the game is not in progress anymore

            /**
             * If we get here then the min and max values are valid and we can save the
             * currentGameRandomNumber
             */
            currentGameRandomNumber = (int) Math.floor(Math.random() * (max - min + 1) + min);

            /**
             * Handle the users guesses with another loop, note we now creating the nested
             * loop block scope
             */
            while (!correct) {
                /**
                 * Prompt to user to make a guess
                 */
                System.out.println("Please guess the number from " + min + " to " + max + " enter q or Q to quit: ");

                /**
                 * Update userInput by reading in user's guess utilizing scnr.nextline()
                 */
                userInput = scnr.nextLine();

                /**
                 * handle if user decided to quit
                 */
                if (userInput.equals("q") || userInput.equals("Q")) {
                    gameInProgress = false;
                    break; // Go to end of program by exiting the gameInProgress while loop
                }

                /**
                 * If user hasn't quit convert that string to a integer so we can compare
                 * them mathematically to the other relevant integers, because user input is a
                 * string data type
                 */
                currentGuess = Integer.parseInt(userInput);

                /*
                 * Increment currentGameGuessCount
                 */
                currentGameGuessCount++;

                /*
                 * Handle whether the user has won, and if not give the user a hint. If they
                 * didn't
                 * guess correctly we give them a hint and reprompt them to guess again by
                 * sending
                 * them back to the start of the while (!correct) loop, which is done by letting
                 * all
                 * lines run in this while block scope run one by one until the end of the loop
                 * block,
                 * it'll automatically rerun this loop and check the condition to see if it
                 * needs to run
                 * again or if it can skip over the while block.
                 */
                if (currentGuess == currentGameRandomNumber) {
                    correct = true;
                    System.out.println("Right!\n");
                    gameCount++;
                    arrayOfGameGuessCounts[gameCount - 1] = currentGameGuessCount;
                    if (showDebuggingLogs)
                        System.out.println("currentGameGuessCount: " + currentGameGuessCount);
                    if (showDebuggingLogs)
                        System.out.println("arrayOfGameGuessCounts: " + Arrays.toString(arrayOfGameGuessCounts));
                    totalGuessCountForAllGames = totalGuessCountForAllGames + currentGameGuessCount; // used for
                                                                                                     // displaying
                                                                                                     // average guess
                                                                                                     // count at end
                                                                                                     // of the game
                    if (showDebuggingLogs)
                        System.out.println("totalGuessCountForAllGames: " + totalGuessCountForAllGames);
                } else if (currentGuess > currentGameRandomNumber) {
                    System.out.println("Lower - try again");
                    continue; // if they didn't guess correctly reprompt them to guess again and send them
                              // back to the start
                    // of the while block
                } else if (currentGuess < currentGameRandomNumber) {
                    System.out.println("Higher - try again");
                    continue; // if they didn't guess correctly reprompt them to guess again and send them
                              // back to the start
                    // of the while block
                }
            }
            if (gameInProgress == false)
                break;// continue runtime by executing code after the while (gameInProgress) loop
                      // since the game is not in progress anymore

            /*
             * Reset the necessary variables to a intial game state for the next round of
             * the game
             */
            userInput = null;
            splitUserInput = null;
            min = 0;
            max = 0;
            validMaxandMin = false;
            currentGameRandomNumber = 0;
            currentGuess = 0;
            correct = false;
            currentGameGuessCount = 0;
            // do not modify gameCount, or totalGuessCountForAllGames

            // let the user repeat the game by going to the top of the while(gameInProgress)
            // loop since we are at end of the while(gameInProgress) loop
        }
        // Outside while gameInProgress loop, because we are infact not in progress
        // anymore

        /*
         * If user quits and they've played at least one game, then display to terminal
         * the
         * game summary
         */
        if (gameCount > 0) {
            /*
             * Calculate total number of guesses for all games iterating over the
             * arrayOfGameGuessCounts and summing the numbers up
             */
            double totalNumberOfGuessesForAllGames = 0;
            for (int i = 0; i < arrayOfGameGuessCounts.length; i++) {
                totalNumberOfGuessesForAllGames = totalNumberOfGuessesForAllGames + arrayOfGameGuessCounts[i];
            }
            if (showDebuggingLogs)
                System.out.println("totalNumberOfGuessesForAllGames: " + totalNumberOfGuessesForAllGames);

            /*
             * Calculate the average guess count
             */
            double averageGuessCount = totalNumberOfGuessesForAllGames / gameCount;

            if (showDebuggingLogs) {
                System.out.println("arrayOfGameGuessCounts.length: " + arrayOfGameGuessCounts.length);
            }

            System.out.println("Game Summary");
            System.out.println("You played the game " + gameCount + " times");
            System.out.println("Your average guesses were " + averageGuessCount + "\n");
        } else {
            System.out.println("Game Over");
        }

        /*
         * close scanner
         */
        scnr.close();
        return; // end program
    }
}
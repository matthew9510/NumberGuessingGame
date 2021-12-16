
package numberguessinggame;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

   
    public static void main(String[] args) {
       
       System.out.println("Welcome to Guess a Number Game");
       System.out.println("The system will generate a random # that you will try to guess");
       System.out.println("Enter 'q' to quit if you're done");
       System.out.println("The system will then output the average guesses it took for you");
       System.out.println("Please enter the min and max values for the number range in integer, q or Q to quit:");
       
       Scanner scnr = new Scanner(System.in);
       Random rand = new Random();
       
       int max = scnr.nextInt();
       int min = scnr.nextInt();
       int num = (int) Math.floor(Math.random()*(max-min+1)+min);
       int guess;
       int guessCount = 0;
       int gameCount = 0;
       boolean correct = false;
       char c = scnr.next().charAt(0); 
     
       while (c != 'q' && c != 'Q') {
        if (c == 'q' || c == 'Q') {
          System.out.println("I'm quitting per your command");
          System.out.println("Game Over");
          break;
        }
        else if (min > max) {
          System.out.println("Min is > max so try again");
        }
        else if (min < 0 || max < 0) {
            System.out.println("You used a negative number for the range so try again");
        }
        else if (min > 10000|| max > 10000) {
            System.out.println("You exceeded the max value for max (10000) so try again");
        }
        }
     
       while (!correct) {
           System.out.println("Please guess the number from " + min + " to " + max + " enter q or Q to quit: ");
           guess = scnr.nextInt();
           guessCount++;
           
        if (guess==num) {
            correct = true;
            System.out.println("Right!");
            gameCount++;
            break;
       }
        else if (guess > num) {
            System.out.println("Lower - try again");
        }
        else if (guess < num) {
            System.out.println("Higher - try again");
        }
       
       } 
       double averageGuess = guessCount/gameCount;
       System.out.println("Game Summary");
       System.out.println("You played the game " + gameCount + " times");
       System.out.println("Your average guesses were " + averageGuess);
       
    
    }
    
}

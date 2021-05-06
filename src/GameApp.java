/*
 * Application for Game class
 *
 * @author Eren Karakas
 * @date   6/5/2021
 * @version 1.00
 */


import java.util.Scanner;

public class GameApp {

    /**
     * @param args The command line arguments
     */
    public static void main( String[] args ) {
        String userAnswer;
        Scanner scanner = new Scanner( System.in );

        /*
        Create a Game object and play with it
        Then ask if the user wants to play again
        Continue in the loop if the answer is yes
        */
        do {
            Game blackjack = new Game();
            blackjack.play();

            System.out.print( "\nPlay again? (y/n): " );
            userAnswer = scanner.nextLine();
        }
        while ( userAnswer.equals( "y" ));

        if ( userAnswer.equals( "n" ) ) {
            System.out.println("Thanks for playing - Goodbye!");
        }
    }
}

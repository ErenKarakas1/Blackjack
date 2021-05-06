/*
 * Game class
 *
 * @author Eren Karakas
 * @date   6/5/2021
 * @version 1.00
 */


import java.util.Scanner;

public class Game {

    private Deck gameDeck;
    private Card[] playerHand;
    private Card[] dealerHand;
    private int playerCards;
    private int dealerCards;

    /**
     * A constructor to create a Game type object
     * Initializes a new Deck and two arrays to represent hands
     */
    public Game() {
        gameDeck = new Deck();

        playerHand = new Card[11];
        dealerHand = new Card[11];

        playerCards = 0;
        dealerCards = 0;
    }

    /**
     * Prints the parameter's hand
     * @param person Person whose hand should be printed
     */
    public void printHand( String person ) {
        if ( person.equals( "player" ) ) {
            System.out.println( "Your hand: " );

            for (int i = 0; i < playerCards; i++) {
                System.out.println( playerHand[i] );
            }
        }

        if ( person.equals( "dealer" ) ) {
            System.out.println( "Dealer hand: " );

            for (int i = 0; i < dealerCards; i++) {
                System.out.println( dealerHand[i] );
            }
        }
        System.out.println();
    }

    /**
     * Looks for the conditions to determine if the Game should end, prints
     * Game's results
     *
     * @param person Person who made a move last
     * @return A boolean to represent if Game should end or not
     */
    public boolean determineResult( String person ) {
        boolean gameEnded;
        int playerPoints;
        int dealerPoints;

        gameEnded = false; //Boolean to check if game should end

        playerPoints = calculateHand("player");

        if ( person.equals( "player" ) ) {
            //Check if the player exceeded 21, print dealer's hand since he couldn't make a move
            if ( playerPoints > 21 ) {
                printHand("dealer");

                playerLost();
                gameEnded = true;
            }
            //Check if the player achieved BlackJack, print dealer's hand since he couldn't make a move
            else if ( playerPoints == 21 ) {
                System.out.println("BlackJack!!\n");

                printHand("dealer");

                playerWon();
                gameEnded = true;
            }
        }
        //Check if the dealer exceeded 21
        dealerPoints = calculateHand("dealer");

        if ( person.equals( "dealer" ) && dealerPoints > 21 ) {
            playerWon();
            gameEnded = true;
        }

        //Determine who should win if the turns are over
        if ( person.equals( "everyoneStayed" ) ) {
            if ( playerPoints == dealerPoints) {
                playerLost();
            }
            else if ( playerPoints < dealerPoints) {
                playerLost();
            }
            else {
                playerWon();
            }
            gameEnded = true;
        }

        return gameEnded;
    }

    /**
     * Plays the game until someone wins
     */
    public void play() {
        boolean gameEnded;
        boolean didMove;

        //Deal two cards to each
        receiveCard( "player", 2 );
        receiveCard( "dealer", 2 );

        //Dealer's cards are hidden until their turn
        printHand( "player" );

        //Player can keep playing until he stays or game ends
        do {
            didMove = playTurn("player");
            gameEnded = determineResult("player");
        }
        while ( didMove && !gameEnded );

        //If game didn't end, print dealer's hand and play
        if ( !gameEnded ) {
            printHand("dealer");
            didMove = playTurn("dealer");
        }

        //Dealer can keep playing until he stays or game ends
        while ( didMove && !gameEnded ) {
            didMove = playTurn( "dealer" );
            gameEnded = determineResult( "dealer" );
        }

        //If the game didn't end, check for final results
        if ( !gameEnded ) {
            determineResult("everyoneStayed");
        }
    }

    /**
     * Person receives card, their card numbers are updated
     * @param person Who should receive the card
     * @param count How many cards should they receive
     */
    private void receiveCard( String person, int count ) {
        if ( person.equals( "player" ) ) {
            for ( int i = 0; i < count; i++ ) {
                playerHand[playerCards] = gameDeck.dealCard();
                playerCards++;
            }
        }

        if ( person.equals( "dealer" ) ) {
            for (int i = 0; i < count; i++) {
                dealerHand[dealerCards] = gameDeck.dealCard();
                dealerCards++;
            }
        }
    }

    /**
     * Calculates how much person's hand holds
     * @param person Whose hand should be calculated
     * @return Sum of cards in hand
     */
    private int calculateHand( String person ) {
        int handSum;
        int length;
        Card[] hand;

        //Assign whose hand should be calculated
        if ( person.equals( "player" ) ) {
            hand = playerHand;
            length = playerCards;
        }
        else {
            hand = dealerHand;
            length = dealerCards;
        }

        handSum = 0;
        for ( int i = 0; i < length; i++ ) {
            handSum += hand[i].getValue();
        }

        return handSum;
    }

    /**
     * Plays a turn if the player wants or the dealer should
     * @param person Who should play their turn
     * @return A boolean to represent if the turn was played
     */
    private boolean playTurn( String person ) {
        int selectedOption;
        boolean turnPlayed;

        turnPlayed = false;
        Scanner scanner = new Scanner( System.in );

        if ( person.equals( "player" )) {
            System.out.print("(1) Hit or (2) Stay: ");
            selectedOption = scanner.nextInt();

            if (selectedOption == 1) {
                receiveCard( person, 1);
                printHand( person );

                turnPlayed = true;
            }
        }
        else {
            if (calculateHand( person ) < 17 ) {
                receiveCard( person, 1 );
                printHand( person );

                turnPlayed = true;
            }
        }
        return turnPlayed;
    }

    /**
     * Displays necessary losing message
     */
    private void playerLost() {
        System.out.println( "Player: " + calculateHand( "player" ) + " Dealer: " + calculateHand( "dealer" ) );
        System.out.println( "Sorry - you lose" );
    }

    /**
     * Displays necessary winning message
     */
    private void playerWon() {
        System.out.println( "Player: " + calculateHand( "player" ) + " Dealer: " + calculateHand( "dealer" ) );
        System.out.println( "Congrats - You win!" );
    }
}

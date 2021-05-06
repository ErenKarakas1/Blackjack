/*
 * Deck class
 *
 * @author Eren Karakas
 * @date   6/5/2021
 * @version 1.00
 */


import java.util.Random;

public class Deck {

    private Card[] deck;
    private int topCard;

    /**
     * A constructor to create a Deck type object
     */
    public Deck() {
        deck = new Card[52];

        initialize();
        shuffle();
        topCard = 0;
    }

    /**
     * Fills the deck with cards
     */
    public void initialize() {
        int counter;
        counter = 0;

        for ( int i = 1; i < 5; i++ ) {
            for ( int j = 1; j < 14; j++ ) {
                Card currentCard = new Card( i, j );
                deck[counter] = currentCard;
                counter++;
            }
        }
    }

    /**
     * Randomly shuffles the deck using Random class
     */
    public void shuffle() {
        int firstIndex;
        int secondIndex;

        Random generator = new Random();

        for ( int i = 0; i < 100; i++ ) {
            firstIndex = generator.nextInt( 52 );
            secondIndex = generator.nextInt( 52 );

            //Swap two cards using a temporary variable to hold the object
            Card temp = deck[firstIndex];
            deck[firstIndex] = deck[secondIndex];
            deck[secondIndex] = temp;
        }
    }

    /**
     * Deals a card and increments topCard
     * @return Dealt card
     */
    public Card dealCard() {
        Card firstCard;

        firstCard = deck[topCard];
        topCard++;

        return firstCard;
    }

    /**
     * Sets how a Deck should be displayed when printed
     * @return Deck's string representation
     */
    public String toString() {
        String finalString;

        finalString = "";

        for ( Card card : deck ) {
            finalString += card + "\n";
        }

        return finalString;
    }
}

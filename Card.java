/**
 * @(#)Card.java
 *
 *
 * @author Lori
 * @version 1.00 2021/04/28
 */


public class Card {
	private int suit;
	private int value;
	
	/**
	 * Creates a Card object. 
	 * @param suit the integer value from 1 - 4 representing the suit (hearts, diamonds, spades, clubs
	 * @param value the value of the card from 1 - 13
	 */
    public Card(int suit, int value) {
    	this.suit = suit;
    	this.value = value;
    }
    /**
	 * Returns the suit of the Card object 
	 * @return the int suit value of the card	
	 */
	public int getSuit(){
    	return suit;
    }
	/**
	 * Returns the value of the Card object 
	 * @return the int value of the card	
	 */
	public int getValue(){
    	if(value < 10){
    		return value;
    	}
    	return 10;
    }
	/**
	 * Returns the String representing the suit of the Card - (Hearts(1), Diamonds(2), Spades(3), Clubs(4)
	 * @return the String name for the suit 
	 */
    public String getSuitAsString(){
    	String suitString;
    	if ( suit == 1 )
    		suitString = "Hearts";
    	else if ( suit == 2 )
    		suitString = "Diamonds";
    	else if ( suit == 3 )
    		suitString = "Spades";
    	else
    		suitString = "Clubs";  				
    	return suitString;
    }
	/**
	 * Returns the String representing the value of the Card
	 * Ace(1), Jack(11), Queen(12), King(13). Numeric value for others.
	 * @return the String name for the value - 
	 */
    public String getValueAsString(){
 		String valString;
		if ( value == 1 )
			valString = "Ace";
    	else if ( value == 11 ) 
    		valString = "Jack";
		else if ( value == 12 ) 
    		valString = "Queen";
    	else if ( value == 13 ) 
    		valString = "King";
    	else
    		valString = String.valueOf( value );
 		return valString;
    }
	/**
	 * Returns the String representation of the Card object
	 * @return the String representation of the Card: ValueAsString of SuitAsString
	 */
    public String toString(){
		return getValueAsString()+" of "+getSuitAsString();
    }
}
package com.olympic.cis143.finalAssignment;

public class Card {
//holds the different construction values for the cards
   public enum Suit {
        HEARTS,
        CLUBS,
        DIAMONDS,
        SPADES
    }

   public enum Value {
        ACE,
        KING,
        QUEEN,
        JACK,
        _10,
        _9,
        _8,
        _7,
        _6,
        _5,
        _4,
        _3,
        _2
    }

    private Suit suit;
    private Value value;
//creates card objects
    public Card(final Suit suit, final Value value) {
        this.suit = suit;
        this.value = value;
    }
    //Returns Suit of Card
    public Suit getSuit() {
        return this.suit;
    }
    //returns Value of Card
    public Value getValue() {
        return value;
    }
//checks if two cards are equal
    public boolean equals(final Card card) {
        if (card.value == this.value && card.suit == this.suit) {
            return true;
        }
        return false;
    }
    //returns a number value based on the string that is assigned to the cards value
    public int getNumber() {
    	switch(this.value) {
    	case ACE: return 11;
    	case KING: return 10;
    	case QUEEN: return 10;
    	case JACK: return 10;
    	case _10: return 10;
    	case _9: return 9;
    	case _8: return 8;
    	case _7: return 7;
    	case _6: return 6;
    	case _5: return 5;
    	case _4: return 4;
    	case _3: return 3;
    	case _2: return 2;
    	default: return 0;//this shouldn't ever happen
    	}
    }

	@Override
	//returns a string version of the card objects
    public String toString() {
        return "[ Suit: " + this.suit + ", Value: " + value + " ]";
    }
}
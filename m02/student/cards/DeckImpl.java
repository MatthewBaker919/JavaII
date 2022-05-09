package com.olympic.cis143.m02.student.cards;

import java.util.Collections;
import java.util.Stack;

import com.olympic.cis143.m02.student.cards.Card.Suit;
import com.olympic.cis143.m02.student.cards.Card.Value;

/**
 * Note that you can think of the deck implementation as being an iterator in that its creates and used once. Meaning, when a card is
 * dealt is  MUST be removed from the deck.
 */
public class  DeckImpl implements Deck{

    /**
     * This will be the stack object for you to work with.
     */
    private Stack<Card> deck = new Stack<>();

    /**
     * Const.
     * @param jokers True if you want jokers in this deck.
     */
    public DeckImpl(final boolean jokers) {
        this.createDeck(jokers);
    }

    /**
     * Private. THis is the place where you will need to create a deck of cards. You can iterate through
     * their enums in Card.
     *
     * Some rules:
     * ============
     * 1. Note the boolean jokers parameter. If true add the Jokers to the deck with a Card.Suite.NONE.
     * 2. Do not create cards, other than jokers, with a Card.Suite.NONE.
     *
     * Outcome
     * ========
     * if jokers == true, 54 cards will be created.
     * if jokers == false, 52 cards are created.
     *
     * @param jokers True if you want jokers added to the deck.
     */
    private void createDeck(final boolean jokers) {
    	//jokers variable should already be true or false so there is no need to use == to confirm it
    	if(jokers) { 
    		//used to push variables into stack.
    		//Putting (Card card = new Card(...)) didn't work for reasons unbeknownst to me
    		Card card;
			//pushes jokers into the deck first if true
    		deck.push(card = new Card(Suit.NONE , Value.JOKER));
    		deck.push(card = new Card(Suit.NONE , Value.JOKER));
    	}
    	//rest of cards are pushed in irregardless if the value is true or not
    	for(Suit suit : Suit.values()){
    		//Stops suits of type NONE from entering deck
    		if(suit != Suit.NONE) {
    			for(Value value : Value.values()) {
    				//Stops values of JOKER from entering deck
    				if(value != Value.JOKER) {
    					Card card;//initializes a Card variable "card" to help create the card objects
						deck.push(card = new Card(suit,value));
    				}
    			}
    		}
    	}
    }

    /**
     * Gets the deck.
     * @return The deck.
     */
    public Stack<Card> getDeck() {
        return this.deck;
    }
    /**
     * Randomize and shuffle the deck of cards.
     */
    public void shuffle() {
    	Collections.shuffle(deck);
    }
    
    /**
     * True if the deck has cards remaining else false.
     * @return
     */
    public boolean hasNext() {
    	//tests if the deck is empty
    	if(deck.empty()) return false;
    	//if the deck fails the empty test it returns true by default
    	else return true;
    }

    /**
     * Always call the hasNext() method before calling this method.
     * This method should get the next card in the deck.
     *
     * Outcome
     * =======
     * The method will remove the next from the deck and return it in the method.
     *
     * If the deck is empty it should through a RuntimeException.
     * @return
     */
    public Card dealCard() throws RuntimeException {
        if (this.hasNext()){
        	return deck.pop();
        }
        else {
        	throw new RuntimeException("Deck is empty");
        }
    }
}
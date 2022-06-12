package com.olympic.cis143.finalAssignment;

import java.util.Collections;
import java.util.Stack;

import com.olympic.cis143.finalAssignment.Card.Suit;
import com.olympic.cis143.finalAssignment.Card.Value;

public class  DeckImpl{
     Stack<Card> deck = new Stack<>();

    public DeckImpl(int count) {
        this.createDeck(count);
    }

    private void createDeck(final int count) {
    	if(count == 0) throw new RuntimeException("Cannot Create A Deck With Zero Cards");
    	for(int i = 0; i < count; i++) {
	    	for(Suit suit : Suit.values()){
	    		for(Value value : Value.values()) {
	    				Card card;//initializes a temporary Card variable "card" to help create the card objects
						deck.push(card = new Card(suit,value));
	    		}
    		}
    	}
    	//shuffles the deck upon creation. There should never be a time when an unshuffled deck is needed
    	this.shuffle();
    }

    //returns the created deck after shuffling it
    public Stack<Card> getDeck() {
    	return this.deck;
    }
    
    //shuffles the deck
    public void shuffle() {
    	Collections.shuffle(deck);
    }
    
    //Checks if deck is empty
    public boolean hasNext() {
    	//tests if the deck is empty
    	if(deck.empty()) return false;
    	//if the deck fails the empty test it returns true by default
    	else return true;
    }
    //returns a card and removes it from the deck
    public Card dealCard() throws RuntimeException {
        if (this.hasNext()){
        	return deck.pop();
        }
        else {
        	throw new RuntimeException("Deck is empty");
        }
    }
}
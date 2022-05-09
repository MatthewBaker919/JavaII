package com.olympic.cis143.m03.student.homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Iterator;



public class DeckIteratorImpl implements Deck
{
	List<Card> deck = new ArrayList<Card>();
	
	public DeckIteratorImpl(boolean joker) {
		if(joker) {
			Card jokers = new Card(Card.Suit.NONE, Card.Value.JOKER);
			deck.add(jokers);
			deck.add(jokers);
		}
		for(Card.Suit suit : Card.Suit.values()){
    		//Stops suits of type NONE from entering deck
    		if(suit != Card.Suit.NONE) {
    			for(Card.Value value : Card.Value.values()) {
    				//Stops values of JOKER from entering deck
    				if(value != Card.Value.JOKER) {
    					Card card;//initializes a Card variable "card" to help create the card objects
						deck.add(card = new Card(suit,value));
    				}
    			}
    		}
    	}
	}
	
	public List<Card> getDeck()
	{
		return this.deck;
	}

	public void shuffle()
	{
		Collections.shuffle(deck);
	}

	public boolean hasNext()
	{
		return !deck.isEmpty();
	}
	
	public Card dealCard()
	{
		Iterator<Card> it = deck.listIterator();
		//I was having a problem with a "Concurrent Modification Exception" being thrown when the iterator was not local to this value
		return it.next();
	}

}

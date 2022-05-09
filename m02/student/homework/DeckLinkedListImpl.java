package com.olympic.cis143.m02.student.homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

import com.olympic.cis143.m02.student.cards.Card;
import com.olympic.cis143.m02.student.cards.Card.Suit;
import com.olympic.cis143.m02.student.cards.Card.Value;

/**
 * Note that you can think of the deck implementation as being an iterator in that its creates and used once. Meaning, when a card is
 * delt is  MUST be removed from the deck.
 */
public class DeckLinkedListImpl {
    /**
     * This will be the stack object for you to work with.
     */
    private Deque<Card> deck = new LinkedList<>();

    /**
     * Const.
     * @param jokers True if you want jokers in this deck.
     */
    public DeckLinkedListImpl(final boolean jokers) {
        this.createDeck(jokers);
    }

    /**
     * Private. THis is the place where you will need to create a deck of cards. You can iterate throug
     * ther enums in Card.
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
    	if(jokers) {
    		Card card = new Card(Suit.NONE, Value.JOKER);
    		deck.addFirst(card);
    		deck.addFirst(card);
    	}
    	for(Suit suit : Suit.values()) {
    		//stops unwanted suit
    		if(suit != Suit.NONE) {
    			for(Value value : Value.values()) {
    				//stops unwanted value
    				if(value != Value.JOKER) {
    					Card card;
    					deck.addLast(card = new Card(suit,value));
    				}
    			}
    		}
    	}
    }
     	
    /**
     * Gets the deck.
     * @return The deck.
     */
    public Deque<Card> getDeck() {
        return this.deck;
    }
    /**
     * Randomize and shuffle the deck of cards.
     */
    
    //this tester doesn't seem to work. It was passing even when this was empty
    public void shuffle() {
    	Stack<Card> tempDeck = new Stack<>();
    	//creates this to use as a constant otherwise the pop will change the value
    	int size = deck.size();
    	for(int i = 0; i < size; i++){
    		tempDeck.push(deck.pop());
    	}
    	Collections.shuffle(tempDeck);
    	for(Card c : tempDeck){
    		deck.push(c);
    	}
    	System.out.println(this.getDeck().size());
    	for(Card c : deck) {
    		System.out.println(c);
    	}
    }

    /**
     * True if the deck has cards remaining else false.
     * @return
     */
    public boolean hasNext() {
        if(deck.isEmpty()){
        	return false;
        }
        else {
        	return true;
        }
    }

    /**
     * Always call the hasNext() method before calling this method.
     * This method should get the next card in the deck.
     *
     * Outcome
     * =======
     * The method will remove the next from the deck and return it in the method.
     *
     * If the deck is empty it should throw a RuntimeException.
     * @return
     */
    public Card dealCard() {
        if(!this.hasNext()) {
        	throw new RuntimeException("The deck is empty");
        }
    	return deck.pop();
    }
}

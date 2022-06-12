package com.olympic.cis143.finalAssignment;

import com.olympic.cis143.finalAssignment.Card.Value;

public class Player
{
	/*
	 *initialized number is the largest amount of cards that 
	 *is possible to acquire without busting in a two deck game 
	 *+ 1 since they will bust after one more card
	 */
	//M01 write a method that manipulates an array
	protected Card[] hand = new Card[15];
	//used for Tests
	public Player(DeckImpl deck) {
		Card a = deck.dealCard();
		Card b = deck.dealCard();
		this.createHand(a,b);
	}
	public Player(Card a, Card b) {
		this.createHand(a, b);
	}
	public Player() {
		Card a = BlackJack.deck.dealCard();
		Card b = BlackJack.deck.dealCard();
		this.createHand(a,b);
	}
	//creates the players hand
	public void createHand(Card a, Card b) {
		hand[0] = a;
		hand[1] = b;
	}
	//creates and returns the players hand
	public Card[] createReturnHand(Card a, Card b) {
		hand[0] = a;
		hand[1] = b;
		return hand;
	}
	//returns the players hand
	public Card[] getHand() {
		return hand;
	}
	//creates a new hand after destroying the old hand
	//used at the top of the round
	public void newHand() {
		try {
			for(int i = 0; i < hand.length; i++) {
				hand[i]=null;
			}
			hand[0]=BlackJack.deck.dealCard();
			hand[1]=BlackJack.deck.dealCard();
			i=0;
		}
		catch(RuntimeException err) {
			BlackJack.newDeck();
		}
	}
	int i = 0;//number of times the hand has been hit
	//returns card from the top of the deck
	public void hit(DeckImpl deck) {
		hand[i+2] = deck.dealCard();
		i++;
	}
	//adds the value of the hand together
	public int addHand(){
		int n = 0;
		for(Card card : hand){
			if(card==null) break;
			n += card.getNumber();
		}
		return n;
	}
	//returns a string version of the hand
	public String handToString() {
		String ret = "";
		for(Card card : this.hand) {
			if (card != null) {
				ret+=card;
			}
			else break;
		}
		return ret;
	}
	//checks if a busted hand has an ace. If it does the value is lowered by ten
	public int bustAceCheck(){
		if(this.addHand()>21){
			for(Card card : hand) {
				if(card==null) break;
					if(card.getValue()==Value.ACE)
						return this.addHand()-10;
					else return this.addHand();
			}
		}
		//return this.addHand();
		return this.addHand();
	}
	//checks whether a player has busted or not
	public boolean bustCheck() {
		if(this.addHand()>21) {
			if(this.bustAceCheck()>21) return true;
		}
		else return false;
		//should never make it here
		return false;
	}
	//checks whether the value of a hand is 21
	public boolean isTwentyOne() {
		if(this.addHand()==21)
			return true;
		else if(this.bustAceCheck()==21)
			return true;
		else if(this.addHand()!=21)
			return false;
		else return false;
	}
	
	
}

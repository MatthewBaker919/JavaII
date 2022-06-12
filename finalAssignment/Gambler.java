package com.olympic.cis143.finalAssignment;

public class Gambler extends Player
{
	//generates a gambler with 2 random cards
	public Gambler(DeckImpl deck)
	{
		super(deck);
	}
	//creates a gambler with two specific cards. Used for testing
	public Gambler(Card a, Card b) {
		super(a,b);
	}
	//the money used in gambling. Tracked for fun
	private int bank = 100;
	//returns the amount of money a gambler has
	public int getBank() {
		return this.bank;
	}
	//subtracts the bet from the bank
	public void loseBank() {
		this.bank-=BlackJack.getBet();
	}
	//subtracts a custom amount from the bank
	public void loseBank(int n) {
		this.bank-=n;
	}
	//adds bet to the bank
	public void winBank() {
		this.bank+=BlackJack.getBet();
	}
	//adds custom bet to the bank
	public void winBank(double n) {
		this.bank+=n;
	}
	//splits the hand. Available to player if they have to cards of the same value
	Card[] splitHand = new Card[4];
	public void split() {
		splitHand[0]=hand[0];
		splitHand[2]=hand[1];
		splitHand[1]=BlackJack.deck.dealCard();
		splitHand[3]=BlackJack.deck.dealCard();
	}
	//checks if a hand can be split
	public boolean isSplitable() {
		return super.hand[0].getValue()==super.hand[1].getValue();
	}
	//returns a hand that has been split
	public Card[] getSplitHand(){
		return splitHand;
	}
}
package com.olympic.cis143.finalAssignment;

public final class Dealer extends Player
{

	public Dealer(DeckImpl deck)
	{
		super(deck);
	}
	//returns the first card from dealers hand to be displayed
	public Card getTopCard() {
		Card[] card = super.getHand();
		return card[0];
	}
	//dealer hits on anything below 17
	public void dealerTurn() {
		while(this.addHand()<17) {
			this.hit(BlackJack.deck);
		}
	}
}

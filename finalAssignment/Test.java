package com.olympic.cis143.finalAssignment;

import com.olympic.cis143.finalAssignment.Card.Suit;
import com.olympic.cis143.finalAssignment.Card.Value;

public class Test
{

	public static void main(String[] args)
	{
		Test test = new Test();
		test.testCreateOne();
		test.testCreateTwo();
		test.testPlayer();
		test.testCreatePlayers();
		test.bustCheckTest();
		test.testIsTwentyOne();
		test.testNewHand();
		test.testHit();
		test.testAddHand();
		test.testHandToString();
		test.testGetNumber();
		test.testLoseBank();
		test.testWinBank();
		test.testSplit();
		test.testHasNext();
		test.testGetTopCard();
		test.testDealerTurn();
		//test.testStartScreen();
		//test.testMainScreen();
	}
	
	public void testCreateOne() {
		DeckImpl deck = new DeckImpl(1);
		if(deck.getDeck().size() != 52) {
			throw new RuntimeException("Test createOne Failed");
		}
		else System.out.println("Test createOne Succeed");
	}
	public void testCreateTwo() {
		DeckImpl deck = new DeckImpl(2);
		if(deck.getDeck().size() != 104) {
			throw new RuntimeException("Test createTwo Failed");
		}
		else System.out.println("Test createTwo Succeed");
	}
	public void testPlayer() {
		//must be confirmed with the console
		DeckImpl deck = new DeckImpl(1);
		Gambler test = new Gambler(deck);
		Card[] cards = test.getHand();
		for(Card card : cards) {
			if(card==null)break;
			System.out.println(card);
		}
		//fills hand to the max with cards, commented out because it makes the output hard to read
//		for(int i = 0; i < 8; i++) {
//			test.hit(deck);
//		}
//		for(Card card : cards) {
//			System.out.println(card);
//		}
//		System.out.println(test.addHand());
	}
	public void testCreatePlayers() {
		BlackJack test = new BlackJack();
		test.createDeck(2);
		test.createPlayers(2);
		int n = 2;
		int t = 0;
		for(Player player : test.getPlayers()) {
			t++;
			for(Card card : player.getHand()) {
				if(card == null) break;
			}
			//tests adding the hand of multiple players
			System.out.println(player.addHand());
			//tests the check if the cards are up to 21
		}
		if(n==t)System.out.println("testCreatePlayer passed");
		else throw new RuntimeException("testCreatePlayers failed");
	}
	
	public void bustCheckTest() {
		BlackJack test = new BlackJack();
		test.createDeck(2);
		test.createPlayers(1);
		for(Player player : test.getPlayers()) {
			player.hit(BlackJack.deck);
			//System.out.println(player.bustAceCheck());
			if((player.bustAceCheck()>21) == (player.bustCheck())) System.out.println("testBustCheck Passed");
			else {
				for(Card card : player.getHand()) {
					if(card == null) break;
				}
				throw new RuntimeException("testBustCheck Failed");}
		}
	}
	public void testIsTwentyOne(){
		DeckImpl test = new DeckImpl(1);
		Gambler player = new Gambler(test);
		player.hit(test);
		
		if(player.isTwentyOne() == (player.bustAceCheck()==21)) System.out.println("testIsTwentyOne Passed");
		else throw new RuntimeException("testIsTwentyOne Failed");
	}
	public void testNewHand() {
		Player player = new Player();
		String oldCards = player.handToString();
		player.newHand();
		if(player.handToString()!=oldCards) {
			System.out.println("testNewHand Success");
		}
		else throw new RuntimeException("testNewHand fail");
	}
	public void testHit() {
		Player player = new Player();
		DeckImpl deck = new DeckImpl(1);
		String oldPlayer = player.handToString();
		player.hit(deck);
		if(oldPlayer!=player.handToString()) {
			System.out.println("testHit success");
		}
		else throw new RuntimeException("testHit fail");
	}
	public void testAddHand() {
		Card card1 = new Card(Suit.CLUBS,Value._10);
		Card card2 = new Card(Suit.DIAMONDS,Value.JACK);
		Player player = new Player(card1, card2);
		if(player.addHand()==20) {
			System.out.println("testAddHand passed");
		}
		else throw new RuntimeException("testAddHand fail");
	}
	public void testHandToString() {
		//checked manually in console
		Card card1 = new Card(Suit.CLUBS,Value._10);
		Card card2 = new Card(Suit.DIAMONDS,Value.JACK);
		Player player = new Player(card1, card2);
		System.out.println(player.handToString());
		System.out.println(card1.toString()+card2.toString());
	}
	public void testGetNumber() {
		Card card = new Card(Suit.CLUBS,Value._10);
		if(card.getNumber()==10) {
			System.out.println("testGetNumber success");
		}
		else throw new RuntimeException("testGetNumber failed");
	}
	public void testLoseBank() {
		DeckImpl deck = new DeckImpl(1);
		Gambler gambler = new Gambler(deck);
		BlackJack.setBet(10);
		gambler.loseBank();
		if(gambler.getBank()==90) {
			System.out.println("testLoseBank success");
		}
		else throw new RuntimeException("testLoseBank fail");
	}
	public void testWinBank() {
		DeckImpl deck = new DeckImpl(1);
		Gambler gambler = new Gambler(deck);
		BlackJack.setBet(10);
		gambler.winBank();
		if(gambler.getBank()==110) {
			System.out.println("testWinBank success");
		}
		else throw new RuntimeException("testWinBank fail");
	}
	public void testSplit() {
		Card card1 = new Card(Suit.CLUBS,Value._10);
		Card card2 = new Card(Suit.DIAMONDS,Value._10);
		Gambler gambler = new Gambler(card1,card2);
		gambler.split();
		Card[] cards = gambler.getSplitHand();
		if(cards[0]==card1 && cards[2]==card2) {
			System.out.println("testSplit success");
		}
	}
	private void testHasNext()
	{
		DeckImpl deck = new DeckImpl(1);
		for(int i = 0; i<52; i++) {
			deck.dealCard();
		}
		if(!deck.hasNext()) {
			System.out.println("testHasNext success");
		} else {
			throw new RuntimeException("testHasNext fail");
		}
		
	}
	public void testGetTopCard() {
		DeckImpl deck = new DeckImpl(1);
		Dealer dealer = new Dealer(deck);
		if(dealer.getTopCard()==dealer.getHand()[0]) {
			System.out.println("testGetTopCard success");
		}else {
			throw new RuntimeException("testGetTopCard fail");
		}
	}
	public void testDealerTurn() {
		DeckImpl deck = new DeckImpl(1);
		Dealer dealer = new Dealer(deck);
		dealer.dealerTurn();
		if(dealer.addHand()>=17) {
			System.out.println("testDealerTurn success");
		}else {
			throw new RuntimeException("testDealerTurn fail");
		}
	}
	public void testStartScreen() {
		new StartScreen();
	}
	public void testMainScreen() {
		BlackJack.setDeckCount(1);
		BlackJack.setPlayerCount(1);
		new MainScreen();
	}
}
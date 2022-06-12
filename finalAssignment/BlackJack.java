package com.olympic.cis143.finalAssignment;

public class BlackJack{
	
	//initialize statics. Needed later
	public static DeckImpl deck;
	private static int playerCount = 0;
	private static int deckCount = 0;
	private static int bet = 0;
	
	//create toolkit for game methods
	
	//creates deck object
	public void createDeck() {
		deck = new DeckImpl(deckCount);
	}
	public static void newDeck() {
		deck = new DeckImpl(deckCount);
	}
	//same as above. Used for tests
	public void createDeck(int n) {
		deck = new DeckImpl(n);
	}
	//returns number of gambler objects that will be created
	public static int getPlayerCount() {
		return playerCount;
	}
	//sets the number of gamblers to be created
	public static void setPlayerCount(int n) {
		try {
			playerCount = n;
		}
		catch(NumberFormatException err) {
			//number stays the same
			System.out.println("NumberFormatException BlackJack line 27");
		}	
	}
	//sets number of decks to be created
	public static void setDeckCount(int n) {
		try {
			deckCount = n;
		}
		catch(NumberFormatException err) {
			//number stays the same
			System.out.println("NumberFormatException BlackJack line 40");
		}
	}
	//returns number of decks to be created
	public static int getDeckCount() {
		return deckCount;
	}
	public static void setBet(int n) {
		if(n>=0) {
			bet=n;
		}
	}
	public static int getBet() {
		return bet;
	}
	/*creates Gamblers/gamblers*/
	Gambler[] players;
	public void createPlayers(){
		players = new Gambler[playerCount];
		for(int i = 0; i < playerCount; i++) {
			players[i] = new Gambler(deck);
		}
	}
	//creates number players specified. Used for testing
	public void createPlayers(int n){
		players = new Gambler[n];
		for(int i = 0; i < n; i++) {
			players[i] = new Gambler(deck);
		}
	}
	//creates the dealer player
	Dealer dealer;
	public void createDealer() {
		dealer = new Dealer(deck);
	}
	//returns the dealer
	public Dealer getDealer() {
		return dealer;
	}
	//returns all gambler objects as an array
	public Gambler[] getPlayers() {
		return players;
	}
	//moves game to the next player
	static int turn = 0;
	public void turnIterate() {
		turn++;
	}
	//returns the player whose "turn" it is
	public Gambler getCurrentPlayer() {
		Gambler[] player = this.getPlayers();
			return player[turn];
	}
	//returns the turn number
	public static int getTurn() {
		return turn;
	}
	//wipes old hand and begins a new hand
	public void nextHand()
	{
		for(Gambler player : this.getPlayers()) {
			player.newHand();
		}
		this.getDealer().newHand();
		turn = 0;
	}
}
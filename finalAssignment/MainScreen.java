package com.olympic.cis143.finalAssignment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainScreen extends JFrame implements ActionListener//(2) Explain common uses of Interfaces M02
{
	//creates blackjack object
	BlackJack game = new BlackJack();
	
	//initializes window objects
	private JLabel cards;
	private JLabel total;
	private JLabel dealerCard;
	private JLabel turn;
	private JLabel bank;
	private JButton btnHit = new JButton("Hit");
	private JButton btnStand = new JButton("Stand");
	private JButton btnSplit = new JButton("Split");
	private JButton btnDoubleDown = new JButton("Double Down");
	
	Container canvas;
	
	public MainScreen(){
		//creates game objects
		game.createDeck();
		game.createPlayers(/*Player Count Defined on Start Screen*/);
		game.createDealer();
		
		//defines the pre-initialized window objects
		cards = new JLabel("Your Cards Are: " + game.getCurrentPlayer().handToString());
		total = new JLabel("Your Cards Total: " + game.getCurrentPlayer().bustAceCheck());
		turn = new JLabel("Player " + (getPlayer()+1+"'s turn"));
		bank = new JLabel("Your Currently Have: " + game.getCurrentPlayer().getBank());
		//adds the split button if the player can split their hand
		if(game.getCurrentPlayer().isSplitable()) canvas.add(btnSplit);
		
		dealerCard = new JLabel("Dealer's Card: " + game.getDealer().getTopCard());
		
		if(game.getCurrentPlayer().isTwentyOne()) {
			JOptionPane.showMessageDialog(null, "You have 21", "You Have 21", 1);
			game.getCurrentPlayer().winBank(BlackJack.getBet()*2.5);
			game.turnIterate();
			//updates window to display current player's info
			turn.setText("Player " + (getPlayer()+1+"'s turn"));
			cards.setText("Your Cards Are: " + game.getCurrentPlayer().handToString());
			total.setText("Your Cards Total: " + game.getCurrentPlayer().bustAceCheck());
			bank.setText("You Have: " + game.getCurrentPlayer().getBank());
			//adds the split button if the player can split their hand
			if(game.getCurrentPlayer().isSplitable()) canvas.add(btnDoubleDown);
		}
		if(game.getDealer().addHand()==21) {
			JOptionPane.showMessageDialog(null, "Dealer Has 21", "Dealer Has 21", 1);
			for(Gambler player : game.getPlayers()) {
				if(player.addHand()!=21) {
					player.loseBank();
				}
			}
			//updates window to display current player's info
			turn.setText("Player " + (getPlayer()+1+"'s turn"));
			cards.setText("Your Cards Are: " + game.getCurrentPlayer().handToString());
			total.setText("Your Cards Total: " + game.getCurrentPlayer().bustAceCheck());
			bank.setText("You Have: " + game.getCurrentPlayer().getBank());
			if(game.getCurrentPlayer().isSplitable()) canvas.add(btnDoubleDown);
		}
		
		btnHit.addActionListener(this);
		btnStand.addActionListener(this);
		btnSplit.addActionListener(this);
		btnDoubleDown.addActionListener(this);
		
		this.setTitle("Blackjack By Matthew");
		canvas = this.getContentPane();
		this.setLayout(new GridLayout(10,1));
		this.setSize(1000,600);
		this.setLocation(500,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		canvas.add(new JLabel("Welcome to Blackjack by Matthew"));
		canvas.add(turn);
		canvas.add(dealerCard);
		canvas.add(cards);
		canvas.add(total);
		canvas.add(bank);
		canvas.add(btnHit);
		canvas.add(btnStand);
		canvas.add(btnDoubleDown);
		canvas.add(btnSplit);
		btnSplit.setVisible(game.getCurrentPlayer().isSplitable());
	}
	private int getPlayer()
	{
		return BlackJack.getTurn();
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
			try{ if(e.getSource()==btnHit) {
					game.getCurrentPlayer().hit(BlackJack.deck);
					cards.setText("Your Cards Are: " + game.getCurrentPlayer().handToString());
					total.setText("Your Cards Total: " + game.getCurrentPlayer().bustAceCheck());
					if(game.getCurrentPlayer().addHand()>21) {
						if(game.getCurrentPlayer().bustCheck()) {
							JOptionPane.showMessageDialog(null, "Bust", "Bust", 1);
								game.turnIterate();
								//updates window to display current player's info
								turn.setText("Player " + (getPlayer()+1+"'s turn"));
								cards.setText("Your Cards Are: " + game.getCurrentPlayer().handToString());
								total.setText("Your Cards Total: " + game.getCurrentPlayer().bustAceCheck());
								bank.setText("You Have: " + game.getCurrentPlayer().getBank());
								btnSplit.setVisible(game.getCurrentPlayer().isSplitable());
								if(game.getCurrentPlayer().isTwentyOne()) {
									JOptionPane.showMessageDialog(null, "You have 21", "You Have 21", 1);
									game.getCurrentPlayer().winBank(BlackJack.getBet()*2.5);
									game.turnIterate();
									//updates window to display current player's info
									turn.setText("Player " + (getPlayer()+1+"'s turn"));
									cards.setText("Your Cards Are: " + game.getCurrentPlayer().handToString());
									total.setText("Your Cards Total: " + game.getCurrentPlayer().bustAceCheck());
									bank.setText("You Have: " + game.getCurrentPlayer().getBank());
									btnSplit.setVisible(game.getCurrentPlayer().isSplitable());
								}
							
						}
					}
					//checks if the turn is greater than or equal to the player count
					//if true begins the dealers turn
					
					}
					
					else if(e.getSource()==btnStand) {
						if(BlackJack.getTurn()<BlackJack.getPlayerCount()) {
							//iterates turn on stand
							game.turnIterate();
							//updates window to display current player's info
							turn.setText("Player " + (getPlayer()+1+"'s turn"));
							cards.setText("Your Cards Are: " + game.getCurrentPlayer().handToString());
							total.setText("Your Cards Total: " + game.getCurrentPlayer().bustAceCheck());
							bank.setText("You Have: " + game.getCurrentPlayer().getBank());
							btnSplit.setVisible(game.getCurrentPlayer().isSplitable());
							if(game.getCurrentPlayer().isTwentyOne()) {
								JOptionPane.showMessageDialog(null, "You have 21", "You Have 21", 1);
								game.getCurrentPlayer().winBank(BlackJack.getBet()*2.5);
								//updates window to display current player's info
								turn.setText("Player " + (getPlayer()+1+"'s turn"));
								cards.setText("Your Cards Are: " + game.getCurrentPlayer().handToString());
								total.setText("Your Cards Total: " + game.getCurrentPlayer().bustAceCheck());
								bank.setText("You Have: " + game.getCurrentPlayer().getBank());
								btnSplit.setVisible(game.getCurrentPlayer().isSplitable());
							}
						}
					}
					else if(e.getSource()==btnDoubleDown) {
						game.getCurrentPlayer().hit(BlackJack.deck);
						Gambler player = game.getCurrentPlayer();
						if(player.addHand()>game.getDealer().addHand() && player.addHand() <= 21 || (game.getDealer().addHand()>21 && player.addHand()<21)) {
							player.winBank(BlackJack.getBet()*2);
						}
						else {
							player.loseBank(BlackJack.getBet()*2);
						}
						//iterates turn after doubling down
						game.turnIterate();
						//updates window to display current player's info
						turn.setText("Player " + (getPlayer()+1+"'s turn"));
						cards.setText("Your Cards Are: " + game.getCurrentPlayer().handToString());
						total.setText("Your Cards Total: " + game.getCurrentPlayer().bustAceCheck());
						bank.setText("You Have: " + game.getCurrentPlayer().getBank());
						btnSplit.setVisible(game.getCurrentPlayer().isSplitable());
					}
					else if(e.getSource()==btnSplit) {
						//splits the players hand
						game.getCurrentPlayer().split();
						Card[] card = game.getCurrentPlayer().getSplitHand();
						//displays the split hand to the player
						int hand1 = card[0].getNumber()+card[1].getNumber();
						int hand2 = card[2].getNumber()+card[3].getNumber();
						cards.setText("Hand 1: "+ card[0] + card[1] +" Hand 2: " + card[2]+card[3]);
						//determines if the player beats the dealers current hand 
						if(hand1>game.getDealer().addHand()&&hand1<21) {
							game.getCurrentPlayer().winBank();
							JOptionPane.showMessageDialog(null, "Hand 1 Win", "Hand 1 Win", 1);
						}
						else {
							game.getCurrentPlayer().loseBank();
							JOptionPane.showMessageDialog(null, "Hand 1 Lose", "Hand 1 Lose", 1);
						}
						if(hand2>game.getDealer().addHand()&&hand2<21) {
							game.getCurrentPlayer().winBank();
							JOptionPane.showMessageDialog(null, "Hand 2 Win", "Hand 2 Win", 1);
						}
						else {
							game.getCurrentPlayer().loseBank();
							JOptionPane.showMessageDialog(null, "Hand 2 Lose", "Hand 2 Lose", 1);
						}
					}
			}
			//starts the dealers turn once the gamblers have gone
			catch(ArrayIndexOutOfBoundsException err){
				if(BlackJack.getTurn()>=BlackJack.getPlayerCount()) {			
					game.getDealer().dealerTurn();
					dealerCard.setText("The Dealers Cards are " + game.getDealer().handToString() +" "+ "Total: " + game.getDealer().addHand());
					JOptionPane.showMessageDialog(null, "The Dealer Got " + game.getDealer().addHand(), "Dealer Score", 1);
				}
					//checks if the players won or lost their bets
					for(Gambler player : game.getPlayers()) {
						if(player.addHand()>game.getDealer().addHand() && player.addHand() <= 21 || (game.getDealer().addHand()>21 && player.addHand()<21)) {
							player.winBank();
						}
						else if(player.addHand()<game.getDealer().addHand() || player.addHand()>21) {
							player.loseBank();
						}
					}
				//begins new round
				game.nextHand();
				//updates window to display new round info
				dealerCard.setText("Dealer's Card: " + game.getDealer().getTopCard());
				turn.setText("Player " + (getPlayer()+1+"'s turn"));
				cards.setText("Your Cards Are: " + game.getCurrentPlayer().handToString());
				total.setText("Your Cards Total: " + game.getCurrentPlayer().bustAceCheck());
				bank.setText("You Have: " + game.getCurrentPlayer().getBank());
				btnSplit.setVisible(game.getCurrentPlayer().isSplitable());
			}
		}
	}
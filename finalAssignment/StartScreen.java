package com.olympic.cis143.finalAssignment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame implements ActionListener
{
	//elements for window
	JTextField txtPlayerCount = new JTextField(32);
	JTextField txtDeckCount = new JTextField(32);
	JTextField txtBet = new JTextField(32);
	JButton btnStart = new JButton("Start Game");
	public StartScreen() {
		//sets basic window settings
		this.setTitle("BlackJack by Matthew");
		Container canvas = this.getContentPane();
		this.setLayout(new GridLayout(8,1));
		this.setSize(600,400);
		this.setLocation(400,400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//adds objects to the window
		canvas.add(new JLabel("Welcome to Blackjack By Matthew"));
		canvas.add(new JLabel("How Many Players?"));
		canvas.add(txtPlayerCount);
		canvas.add(new JLabel("How Many Decks?"));
		canvas.add(txtDeckCount);
		canvas.add(new JLabel("What is the bet?"));
		canvas.add(txtBet);
		canvas.add(btnStart);
		btnStart.addActionListener(this);
	}
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
		//in if statement for future-proofing incase another button is needed
		if(e.getSource()==btnStart) {
			try {
				//ensures that the text boxes are not empty
				if(txtPlayerCount.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Player Count Connot Be Empty");
					txtPlayerCount.grabFocus();
				} else if(txtDeckCount.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Deck Count Connot Be Empty");
					txtDeckCount.grabFocus();
				}
				else if(txtBet.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Bet Connot Be Empty");
					txtBet.grabFocus();
				} else {
					//sets static values for later use
					BlackJack.setPlayerCount(Integer.valueOf(txtPlayerCount.getText()));
					BlackJack.setDeckCount(Integer.valueOf(txtDeckCount.getText()));
					BlackJack.setBet(Integer.valueOf(txtBet.getText()));

					//calls new GUI then closes
					new MainScreen();
					this.dispose();
					}
			}
			//Catches any text that is not numbers, tells the user to enter a number then clears the text boxes
			catch(NumberFormatException err){
				JOptionPane.showMessageDialog(null, "Please Only Enter Numbers");
				txtPlayerCount.setText("");
				txtDeckCount.setText("");
				txtBet.setText("");
			}
		}
	}
	public int getPlayerCount() {
		try {
			return Integer.valueOf(txtPlayerCount.getText());
		}
		catch(NumberFormatException err){
			
		}
		return 0;
	}
	public int getDeckCount() {
		return Integer.valueOf(txtDeckCount.getText());
	}
	
}

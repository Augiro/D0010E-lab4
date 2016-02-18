package lab4.gui;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.SpringLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import lab4.client.GomokuClient;
import lab4.data.GameGrid;
import lab4.data.GomokuGameState;

/*
 * The GUI class
 */

public class GomokuGUI implements Observer{

	private GomokuClient client;
	private GomokuGameState gamestate;
	
	public static void main(String[] args){
		GomokuClient testclient=new GomokuClient(4008);
		GomokuClient testclient2=new GomokuClient(4007);
		GomokuGameState gamestate=new GomokuGameState(testclient);
		GomokuGameState gamestate2=new GomokuGameState(testclient2);
		GomokuGUI testGUI=new GomokuGUI(gamestate,testclient);
		GomokuGUI testGUI2=new GomokuGUI(gamestate,testclient2);
	}
	
	/**
	 * The constructor
	 * 
	 * @param g   The game state that the GUI will visualize
	 * @param c   The client that is responsible for the communication
	 */
	public GomokuGUI(GomokuGameState g, GomokuClient c){
		this.client = c;
		this.gamestate = g;
		client.addObserver(this);
		gamestate.addObserver(this);
		
		JButton connectButton=new JButton("Connect");
		JButton newGameButton=new JButton("New Game");
		JButton disconnectButton=new JButton("Disconnect");
		
		JLabel messageLabel=new JLabel("Welcome to Gomoku!");
		
		GameGrid testgrid=new GameGrid(5);
		GamePanel testpanel=new GamePanel(testgrid);
		JFrame test=new JFrame();
		JPanel test2=new JPanel();
		
		
		test.setContentPane(test2);
		test2.add(disconnectButton);
		test2.add(connectButton);
		test2.add(newGameButton);
		connectButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				ConnectionWindow testconnectionwindow=new ConnectionWindow(c);
			}	
		});
		disconnectButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				gamestate.disconnect();
			}	
		});
		newGameButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				gamestate.newGame();
			}	
		}); 
		test2.add(messageLabel);
		test2.add(testpanel);
//		test2.addMouseListener(new MouseAdapter(){
//			public void mouseClicked(MouseEvent e){
//				Point lala=e.getPoint();
//				System.out.println(lala);
//				int[] test=testpanel.getGridPosition(lala.x,lala.y);
//				int X=test[0];
//				System.out.println(lala.x);
//				System.out.println(lala.y);
//				int Y=test[1];
//				System.out.println(testgrid.move(X, Y, 1));
//			}
//		});
		test.pack();
		test.setVisible(true);
	}
	
	
	
	public void update(Observable arg0, Object arg1) {
////		 Update the buttons if the connection status has changed
//		if(arg0 == client){
//			if(client.getConnectionStatus() == GomokuClient.UNCONNECTED){
//				connectButton.setEnabled(true);
//				newGameButton.setEnabled(false);
//				disconnectButton.setEnabled(false);
//			}else{
//				connectButton.setEnabled(false);
//				newGameButton.setEnabled(true);
//				disconnectButton.setEnabled(true);
//			}
//		}
//		
////		 Update the status text if the gamestate has changed
//		if(arg0 == gamestate){
//			messageLabel.setText(gamestate.getMessageString());
//		}
//		
	}
	
}

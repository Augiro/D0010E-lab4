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

/**
 * Creates and manages a GamePanel
 */
public class GomokuGUI implements Observer{

	private GomokuClient client;
	private GomokuGameState gamestate;
	private JLabel messageLabel;
	private JButton connectButton;
	private JButton disconnectButton;
	private JButton newGameButton;

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
	
		connectButton=new JButton("Connect");
		newGameButton=new JButton("New Game");
		disconnectButton=new JButton("Disconnect");
		
		messageLabel=new JLabel("Welcome to Gomoku!");
		
		GameGrid grid=g.getGameGrid();
		GamePanel gamepanel=new GamePanel(grid);
		
		JFrame testframe=new JFrame();//f�nstret
		testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel testpanel=new JPanel();//pane d�r allt s�tts in
		
		testpanel.add(disconnectButton);
		testpanel.add(connectButton);
		testpanel.add(newGameButton);
		
		testpanel.add(messageLabel);
		testpanel.add(gamepanel);
		
		
		testframe.setContentPane(testpanel);
		
		// SpringLayout testing Starts
		SpringLayout layout=new SpringLayout();
		testpanel.setLayout(layout);

		layout.putConstraint(SpringLayout.WEST,connectButton,10,SpringLayout.EAST , disconnectButton);
		layout.putConstraint(SpringLayout.WEST,newGameButton,10,SpringLayout.EAST , connectButton);
		
		layout.putConstraint(SpringLayout.NORTH,newGameButton,10,SpringLayout.SOUTH , gamepanel);
		layout.putConstraint(SpringLayout.NORTH,connectButton,10,SpringLayout.SOUTH , gamepanel);
		layout.putConstraint(SpringLayout.NORTH,disconnectButton,10,SpringLayout.SOUTH , gamepanel);
		layout.putConstraint(SpringLayout.NORTH,messageLabel,10,SpringLayout.SOUTH , disconnectButton);
		
		layout.putConstraint(SpringLayout.EAST,testpanel,10,SpringLayout.EAST , newGameButton);
		layout.putConstraint(SpringLayout.SOUTH,testpanel,10,SpringLayout.SOUTH , messageLabel);

		testframe.pack();
		
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
		
		
		gamepanel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Point lala=e.getPoint();
				System.out.println(lala);
				int[] test=gamepanel.getGridPosition(lala.x,lala.y);
				int X=test[0];
				System.out.println(lala.x);
				System.out.println(lala.y);
				int Y=test[1];
				gamestate.move(X, Y);
			}
		});
		
		connectButton.setEnabled(true);
		newGameButton.setEnabled(false);
		disconnectButton.setEnabled(false);
		
		
		testframe.setVisible(true);
		

	}


	/**
	 * Executed whenever notified by observables
	 * @param arg0 Observable object
	 * @param arg1 Object for any additional thingies
     */
	public void update(Observable arg0, Object arg1){
//		 Update the buttons if the connection status has changed
		if(arg0 == client){
			if(client.getConnectionStatus() == GomokuClient.UNCONNECTED){
				connectButton.setEnabled(true);
				newGameButton.setEnabled(false);
				disconnectButton.setEnabled(false);
			}else{
				connectButton.setEnabled(false);
				newGameButton.setEnabled(true);
				disconnectButton.setEnabled(true);
			}
		}
		
//		 Update the status text if the gamestate has changed
		if(arg0 == gamestate){
			messageLabel.setText(gamestate.getMessageString());
		}
		
	}
}


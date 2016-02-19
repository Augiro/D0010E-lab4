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
		
		JFrame frame=new JFrame();//f�nstret
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel=new JPanel();//pane d�r allt s�tts in
		
		panel.add(disconnectButton);
		panel.add(connectButton);
		panel.add(newGameButton);
		
		panel.add(messageLabel);
		panel.add(gamepanel);
		
		
		frame.setContentPane(panel);
		
		SpringLayout layout=new SpringLayout();
		panel.setLayout(layout);

		layout.putConstraint(SpringLayout.WEST,connectButton,10,SpringLayout.EAST , disconnectButton);
		layout.putConstraint(SpringLayout.WEST,newGameButton,10,SpringLayout.EAST , connectButton);
		
		layout.putConstraint(SpringLayout.NORTH,newGameButton,10,SpringLayout.SOUTH , gamepanel);
		layout.putConstraint(SpringLayout.NORTH,connectButton,10,SpringLayout.SOUTH , gamepanel);
		layout.putConstraint(SpringLayout.NORTH,disconnectButton,10,SpringLayout.SOUTH , gamepanel);
		layout.putConstraint(SpringLayout.NORTH,messageLabel,10,SpringLayout.SOUTH , disconnectButton);
		
		layout.putConstraint(SpringLayout.EAST,panel,10,SpringLayout.EAST , newGameButton);
		layout.putConstraint(SpringLayout.SOUTH,panel,10,SpringLayout.SOUTH , messageLabel);

		frame.pack();
		
		connectButton.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				ConnectionWindow connectionwindow=new ConnectionWindow(c);
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
				Point point =e.getPoint();
				System.out.println(point);
				int[] pos=gamepanel.getGridPosition(point.x,point.y);
				int X=pos[0];
				System.out.println(point.x);
				System.out.println(point.y);
				int Y=pos[1];
				gamestate.move(X, Y);
			}
		});
		
		connectButton.setEnabled(true);
		newGameButton.setEnabled(false);
		disconnectButton.setEnabled(false);
		
		
		frame.setVisible(true);
		

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


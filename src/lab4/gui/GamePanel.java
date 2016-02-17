package lab4.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import lab4.data.GameGrid;

/**
 * A panel providing a graphical view of the game board
 */

public class GamePanel extends JPanel implements Observer{

	private final int UNIT_SIZE = 20;
	private GameGrid grid;
	
	public static void main(String[] args){
		GameGrid testgrid=new GameGrid(9);
		JPanel testpanel=new GamePanel(testgrid);
		JFrame test=new JFrame();
		JPanel test2=new JPanel();
		test.setContentPane(test2);
		test2.add(testpanel);
		test.pack();
		test.setVisible(true);
	}
	
	/**
	 * The constructor
	 * 
	 * @param grid The grid that is to be displayed
	 */
	public GamePanel(GameGrid grid){
		this.grid = grid;
		grid.addObserver(this);
		Dimension d = new Dimension(grid.getSize()*UNIT_SIZE+1, grid.getSize()*UNIT_SIZE+1);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setBackground(Color.WHITE);
		//this should possibly be in GomokuGUI?
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Point lala=e.getPoint();
				System.out.println(lala);
				int[] test=getGridPosition(lala.x,lala.y);
				int X=test[0];
				int Y=test[1];
				System.out.println(grid.move(X, Y, 1));
			}
		});
	}

	/**
	 * Returns a grid position given pixel coordinates
	 * of the panel
	 * 
	 * @param x the x coordinates
	 * @param y the y coordinates
	 * @return an integer array containing the [x, y] grid position
	 */
	public int[] getGridPosition(int x, int y){
		int X= x/UNIT_SIZE;
		int Y=y/UNIT_SIZE;
		System.out.println(X+","+Y);
		int[] gridCoordinates={X,Y};
		return gridCoordinates;
		
	}
	
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawgrid(g);
		drawPlayer(g);
		

	}
	
	private void drawPlayer(Graphics g){
		for(int i=0;i<grid.getSize();i++){
			drawplayerColumm(g,i);
		}

	}
	
	private void drawplayerColumm(Graphics g,int X_pos){
		for(int i=0;i<grid.getSize();i++){
//			System.out.println(grid.getLocation(0, i));
			if(grid.getLocation(X_pos, i)==1){
				g.setColor(Color.BLACK);
				g.fillOval(X_pos*UNIT_SIZE, (i*UNIT_SIZE), UNIT_SIZE , UNIT_SIZE);
			}else if((grid.getLocation(0, i)==2)){
				g.setColor(Color.RED);
				g.fillOval(X_pos*UNIT_SIZE, (i*UNIT_SIZE), UNIT_SIZE , UNIT_SIZE);
			}
		}
	}
	
	private void drawgrid(Graphics g){
		for(int columm_nr=0;columm_nr<grid.getSize();columm_nr++){
			drawverticalColumm(g,(columm_nr*UNIT_SIZE));
		}
		
	}
	private void drawverticalColumm(Graphics g,int X_pos){
		for(int rect_nr=0;rect_nr<grid.getSize();rect_nr++){
			g.drawRect(X_pos,(rect_nr*UNIT_SIZE),UNIT_SIZE,UNIT_SIZE);
	}
	}
	}

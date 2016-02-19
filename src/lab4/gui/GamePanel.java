package lab4.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import lab4.data.GameGrid;

/**
 * A panel providing a graphical view of the game board
 */

public class GamePanel extends JPanel implements Observer {

	private final int UNIT_SIZE = 20;
	private GameGrid grid;

	/**
	 * The constructor
	 * 
	 * @param grid
	 *            The grid that is to be displayed
	 */
	public GamePanel(GameGrid grid) {
		this.grid = grid;
		grid.addObserver(this);
		Dimension d = new Dimension(grid.getSize() * UNIT_SIZE + 1, grid.getSize() * UNIT_SIZE + 1);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
		this.setMaximumSize(d);
		this.setBackground(Color.WHITE);
	}

	/**
	 * Returns a grid position given pixel coordinates of the panel
	 * 
	 * @param x
	 *            the x coordinates
	 * @param y
	 *            the y coordinates
	 * @return an integer array containing the [x, y] grid position
	 */
	public int[] getGridPosition(int x, int y) {
		int X = x / UNIT_SIZE;
		int Y = y / UNIT_SIZE;
		int[] gridCoordinates = { X, Y };
		return gridCoordinates;
	}

	/**
	 * Executed whenever notified by observable
	 * @param arg0 The observable
	 * @param arg1 All purpose object
     */
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}

	/**
	 * Redraws the GameGrid visually
	 * @param g The graphics object
     */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawgrid(g);
		drawPlayer(g);
	}
	/**
	 * Draws players
	 * @param g The graphics object
	 */

	private void drawPlayer(Graphics g) {
		for (int columm_nr = 0; columm_nr < grid.getSize(); columm_nr++) {//draws players for each columm on grid
			drawplayerColumm(g, columm_nr);
		}
	}

	private void drawplayerColumm(Graphics g, int columm) {
		for (int row = 0; row < grid.getSize(); row++) {
			//checks which player occupies the square and draws circle in corresponding color
			//otherwhise "empties" square
			if (grid.getLocation(columm, row) == grid.ME) {
				g.setColor(Color.BLACK);
				g.fillOval((columm * UNIT_SIZE), (row * UNIT_SIZE), UNIT_SIZE, UNIT_SIZE);
			} else if ((grid.getLocation(columm, row) == grid.OTHER)) {
				g.setColor(Color.RED);
				g.fillOval((columm * UNIT_SIZE), (row * UNIT_SIZE), UNIT_SIZE, UNIT_SIZE);
			} else {
				g.setColor(Color.WHITE);
				g.fillOval((columm * UNIT_SIZE), (row * UNIT_SIZE), UNIT_SIZE, UNIT_SIZE);
			}
		}
	}
	
	/**
	 * Draws the grid
	 * @param g The graphics object
	 */

	private void drawgrid(Graphics g) {
		for (int columm_nr = 0; columm_nr < grid.getSize(); columm_nr++) {
			drawVerticalColumm(g, (columm_nr * UNIT_SIZE));
		}

	}

	private void drawVerticalColumm(Graphics g, int X_pos) {
		for (int rect_nr = 0; rect_nr < grid.getSize(); rect_nr++) {
			g.drawRect(X_pos, (rect_nr * UNIT_SIZE), UNIT_SIZE, UNIT_SIZE);
		}
	}
}

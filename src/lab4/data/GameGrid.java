package lab4.data;

import java.util.Observable;

/**
 * Represents the 2-d game grid
 */

public class GameGrid extends Observable{
	public static final int EMPTY = 0;
	public static final int ME = 1;
	public static final int OTHER = 2;
	public static final int INROW = 5;
	private int[][] squares;

	/**
	 * Constructor
	 * 
	 * @param size The width/height of the game grid
	 */
	public GameGrid(int size){
		squares = new int[size][size];
	}
	
	/**
	 * Reads a location of the grid
	 * 
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @return the value of the specified location
	 */
	public int getLocation(int x, int y){
		return squares[x][y];
	}
	
	/**
	 * Returns the size of the grid
	 * 
	 * @return the grid size
	 */
	public int getSize(){
		return squares.length;
	}
	
	/**
	 * Enters a move in the game grid
	 * 
	 * @param x the x position
	 * @param y the y position
	 * @param player
	 * @return true if the insertion worked, false otherwise
	 */
	public boolean move(int x, int y, int player){
		if(squares[x][y] != EMPTY) {
			return false;
		} else {
			squares[x][y] = player;
			setChanged();
			notifyObservers();
			return true;
		}
	}
	
	/**
	 * Clears the grid of pieces
	 */
	public void clearGrid(){
		//for(int[] column : squares) {
		//	for(int square : column) {
		//		square = EMPTY;
		//	}
		//}
		for(int i=0; i<getSize(); i++) {
			for(int j=0; j<getSize(); j++) {
				squares[i][j] = EMPTY;
			}
		}
		System.out.println("Grid cleared.");
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Check if a player has 5 in row
	 * 
	 * @param player the player to check for
	 * @return true if player has 5 in row, false otherwise
	 */
	public boolean isWinner(int player){
		if(getSize() < 5) {
			return false;
		}

		int numInRow = 0;

		// Check vertically
		for(int[] column : squares) {
			for(int square : column) {
				numInRow = square == player ? numInRow+1 : 0;
				if(numInRow == INROW) {
					return true;
				}
			}
			numInRow = 0;
		}

		// Horizontally
		for(int i=0; i < getSize(); i++) {
			for(int y=0; y < getSize(); y++) {
				numInRow = squares[y][i] == player ? numInRow+1 : 0;
				if(numInRow == INROW) {
					return true;
				}
			}
			numInRow = 0;
		}

		// Diagonally right
//		for(int i = 4;i < getSize(); i++) {
//			for(int j=0; j<=i; j++) {
//				numInRow = squares[j][i-j] == player ? numInRow+1 : 0;
//				if(numInRow == INROW) {
//					return true;
//				}
//			}
//			numInRow = 0;
//		}
		
		
		// Diagonally right
		for (int y = 4; y < getSize(); y++) {
			for (int x = 0; x < getSize() - 4; x++) {
				if (squares[x][y] != EMPTY) {
					for (int i = 0; i < x; i++) {
						try {
							numInRow = squares[Math.min(x + i, (getSize() - 4))][y - i] == player ? numInRow + 1 : 0;
							// System.out.println("print " + numInRow + " ," +
							// (x + i) + ":" + (y - i));
							if (numInRow == INROW) {
								return true;
							}
						} catch (ArrayIndexOutOfBoundsException exception) {
							System.out.println(
									"print this is x:" + x + "\n this is i:" + i + " \n" + (x + i) + ":" + (y - i));

						}
					}
					numInRow = 0;
				}
			}
		}
		
		// Diagonally left
		for (int x = getSize()-1; x > 0; x--) {
			for (int y = 4; y < getSize(); y++) {
				if (squares[x][y] != EMPTY) {
					for (int i = 0; i < y; i++) {
//						try {
							numInRow = squares[Math.max(x-i, 0)][y - i] == player ? numInRow + 1 : 0;
							// System.out.println("print " + numInRow + " ," +
							// (x + i) + ":" + (y - i));
							if (numInRow == INROW) {
								return true;
							}//						} catch (ArrayIndexOutOfBoundsException exception) {
//							System.out.println(
//									"print this is x:" + x + "\n this is i:" + i + " \n" + (x + i) + ":" + (y - i));
//
//						}

					}
					numInRow = 0;
				}
			}
		}

		// Diagonally left
//		for(int i=0; i<getSize(); i++) {
//			for(int j=0; j<getSize(); j++) {
//				numInRow = squares[j][j] == player ? numInRow+1 : 0;//g�r ett steg f�r l�ngt? (plockat bort numInRow = squares[j][j+i] tillf�lligt
//				if(numInRow == INROW) {
//					return true;
//				}
//			}
//			numInRow = 0;
//		}

		return false;
//	}

	
}

}
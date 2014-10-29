package com.tictactoe3d.game;

/**
 * Represents a particular position on the board.
 * Contains only 2 fields - row and column
 * 
 * @author Prateek Mathur
 *
 */
public class Position {
	/**
	 * Represents the row
	 */
	public int row;
		
	/**
	 * Represents the coloumn
	 */
	public int coloumn;
		
	/**
	 * The public constructor for Position
	 * Initializes a position object with the supplied row and coloumn
	
	 * @param row
	 * @param coloumn
	 */
	public Position(int row, int coloumn)	{
		this.row = row;
		this.coloumn = coloumn;
	}
}
/*  Copyright 2014 Prateek Mathur
    
    Tic Tac Toe 3D is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Tic Tac Toe 3D is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Tic Tac Toe 3D. If not, see <http://www.gnu.org/licenses/>.
 */

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
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
 * Encapsulates a result set of the minimax algorithm.
 * Consists of the updated Board state, and its score.
 *  
 * @author PrateekMathur
 *
 */
public class MinimaxResult {
	/**
	 * Board representing the Minimax result
	 */
	public Board updatedBoard;
	
	/**
	 * The score associated to this board
	 */
	int score;
	
	/**
	 * The public constructor for MinimaxResult.
	 * Initializes a MinimaxResult object.
	 * 
	 * @param boardState The updated board
	 * @param score The score associated with this board 
	 */
	public MinimaxResult(Board board, int score)	{
		updatedBoard = board;
		this.score = score;
	}
	
	/**
	 * The getter method for score
	 * 
	 * @return score The score associated with this object
	 */
	public int getScore()	{
		return score;
	}
	
	/**
	 * The getter method for Board
	 * 
	 * @return updatedBoard The updated Board
	 */
	public Board getUpdatedBoard()	{
		return updatedBoard;
	}
}

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

import java.util.List;

/**
 * Implements the minimax algorithm which is used by the
 * computer to calculate its next move against the player.
 * 
 * @author Prateek Mathur
 *
 */
public class Minimax {
	// We need to do this
	// 1. Create a static method named minimax, which accepts
	// the 2D array of board as argument, adds the best move in it,
	// and returns it.
	public static int minimax(Board board)	{
		int bestScore = maxMove(board);
		return bestScore;
	}

	

	private static int maxMove(Board board) {
		if (isGameOver(board))	{
			return evaluateScore(board);
		}
		else	{
			Board bestBoard = null;
			List<Board> nextMoves = findNextMoves(board);
			board.scores = new int[nextMoves.size()];
			for (Board move : nextMoves)	{
				
			}
		}
	}



	private static int evaluateScore(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static List<Board> findNextMoves(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

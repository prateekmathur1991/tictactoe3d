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
	// To hold the best Move
	private static char[][] bestMove = null;
	
	/**
	 * Returns the next best move of the computer given the current board state
	 * @param board The current board state
	 * @param player Player whose turn it is
	 * 
	 * @return An integer array representing the next best move and its score (score, row, coloum)
	 */
	public static int[] minimax(int[] board, char currentPlayer)	{
		// Find a list of all possible successors
		List<int[]> successors = generateMoves(board);
		
		// Initialize the bestScore
		int bestScore = (currentPlayer == 'X') ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int currentScore;
		int bestRow = -1; 
		int bestCol = -1;
		
		if (successors.isEmpty())	{
			// We have reached the terminal node, which should return a 
			// +ve score if X is playing, and -ve otherwise.
			bestScore = (currentPlayer == 'X') ? 10 : -10;
		}
		else	{
			for (int[] successor : successors)	{
				// We need to see weather this move is optimal
				// for the current player
				if (currentPlayer == 'X')	{
					currentScore = minimax(successor, 'O')[0];
					if (currentScore > bestScore)	{
						bestMove = setBestMove(successor);
						bestScore = currentScore;
						bestRow = successor[0];
						bestCol = successor[1];
					}
				}
				
				else	{
					currentScore = minimax(successor, 'X')[0];
					if (currentScore < bestScore)	{
						bestMove = setBestMove(successor);
						bestScore = currentScore;
						bestRow = successor[0];
						bestCol = successor[1];
					}
				}
			}
		}
		return new int[] {bestScore, bestRow, bestCol};
		
		
	}

	private static char[][] setBestMove(int[] successor) {
		// TODO Auto-generated method stub
		return null;
	}

	private static List<int[]> generateMoves(int[] board) {
		// TODO Auto-generated method stub
		return null;
	}
}

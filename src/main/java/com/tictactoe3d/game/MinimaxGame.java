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

import com.tictactoe3d.Constants;

import java.util.ArrayList;

/**
 * Implements the minimax algorithm which is used by the
 * computer to calculate its next move against the player.
 * 
 * @author Prateek Mathur
 *
 */
public class MinimaxGame {
	/**
	 * The play() should be called by the MinimaxGame instance to execute the
	 * minimax algorithm.
	 * 
	 * @return result A MinimaxResult object containing the result of the game
	 */
	public MinimaxResult minimax(MinimaxBoard board)	{
		ArrayList<Position> possibleMoves = board.getAllPossibleMoves();
		MinimaxBoard bestChild = null;
		int bestScore= Integer.MIN_VALUE;
		
		for (Position position : possibleMoves)	{
			MinimaxBoard child = new MinimaxBoard(board, position, Constants.O);
			int moveScore = max(child);
			
			if (moveScore > bestScore)	{
				bestChild = child;
				bestScore = moveScore;
			}
		}
		
		MinimaxResult result = new MinimaxResult(bestChild, bestScore);
		return result;
	}
	
	private int max(MinimaxBoard child) {
		ArrayList<Position> possibleMoves = child.getAllPossibleMoves();
		if (possibleMoves.isEmpty())	{
			int score = evaluateScore(child);
			return score;
		}
		
		int bestScore = Integer.MIN_VALUE;
		for (Position position : possibleMoves)	{
			MinimaxBoard currentChild = new MinimaxBoard(child, position, Constants.X);
			int moveScore = min(currentChild);
			
			if (moveScore > bestScore )	{
				bestScore = moveScore;
			}
		}
		
		return bestScore;
	}

	private int min(MinimaxBoard child) {
		ArrayList<Position> possibleMoves = child.getAllPossibleMoves();
		if (possibleMoves.isEmpty())	{
			int score = evaluateScore(child);
			return score;
		}
		
		int bestScore = Integer.MAX_VALUE;
		for (Position position : possibleMoves)	{
			MinimaxBoard currentChild = new MinimaxBoard(child, position, Constants.O);
			int moveScore = max(currentChild);
			
			if (moveScore < bestScore )	{
				bestScore = moveScore;
			}
		}
		
		return bestScore;
	}

	private boolean hasWon(MinimaxBoard board, char player)	{
		boolean status = false;
		char [][] boardArray = board.boardArray;
		
		// Check rows
		for (int i = 0; i < 3; i++)	{
			status |= (boardArray[i][0] == player) && (boardArray[i][1] == player) && (boardArray[i][2] == player);
			if (status)	{
				return true;
			}
		}
		
		// Check columns
		for (int i = 0; i < 3; i++)	{
			status |= (boardArray[0][i] == player) && (boardArray[1][i] == player) && (boardArray[2][i] == player);
			if (status)	{
				return true;
			}
		}
		
		// Check left diagonal
		status |= (boardArray[0][0] == player) && (boardArray[1][1] == player) && (boardArray[2][2] == player);
		if (status)	{
			return true;
		}
		
		// Check right diagonal
		status |= (boardArray[0][2] == player) && (boardArray[1][1] == player) && (boardArray[2][0] == player);
		if (status)	{
			return true;
		}
				
		return false;
	}
	
	
	// We have used a deterministic evaluation function for this implementation of MinimaxGame. 
	// The function simply returns a score of +1 if computer wins, -1 if the user wins,
	// and 0 in case of a draw.
	private int evaluateScore(MinimaxBoard board)	{
		if (hasWon(board, Constants.X))	{
			return -1;
		}
		else if (hasWon(board, Constants.O))	{
			return 1;
		}
		
		return 0;
	}
}
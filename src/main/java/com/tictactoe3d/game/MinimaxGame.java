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
	 * This method should be called by the MinimaxGame instance to execute the
	 * minimax algorithm.
	 * 
	 * @return result A MinimaxResult object containing the result of the game
	 */
	public MinimaxResult play(MinimaxBoard board)	{
		ArrayList<Position> possibleMoves = board.getAllPossibleMoves();
		MinimaxBoard bestChild = null;
		int bestScore = Integer.MIN_VALUE;
		
		for (Position position : possibleMoves)	{
			MinimaxBoard child = new MinimaxBoard((MinimaxBoard)board.clone(), position, Constants.O);
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
			MinimaxBoard currentChild = new MinimaxBoard((MinimaxBoard)child.clone(), position, Constants.X);
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
			MinimaxBoard currentChild = new MinimaxBoard((MinimaxBoard)child.clone(), position, Constants.O);
			int moveScore = max(currentChild);
			
			if (moveScore < bestScore )	{
				bestScore = moveScore;
			}
		}
		
		return bestScore;
	}

	private boolean hasWon(MinimaxBoard board, char player)	{
		boolean status = false;
		
		// Check rows
		for (int i = 0; i < 3; i++)	{
			status |= (board.boardArray[i][0] == player) && (board.boardArray[i][1] == player) && (board.boardArray[i][2] == player);
			if (status)	{
				return true;
			}
		}
		
		// Check columns
		for (int i = 0; i < 3; i++)	{
			status |= (board.boardArray[0][i] == player) && (board.boardArray[1][i] == player) && (board.boardArray[2][i] == player);
			if (status)	{
				return true;
			}
		}
		
		// Check left diagonal
		status |= (board.boardArray[0][0] == player) && (board.boardArray[1][1] == player) && (board.boardArray[2][2] == player);
		if (status)	{
			return true;
		}
		
		// Check right diagonal
		status |= (board.boardArray[0][2] == player) && (board.boardArray[1][1] == player) && (board.boardArray[2][0] == player);
		if (status)	{
			return true;
		}
				
		return false;
	}
	
	// A possible heuristic for our game can be this-
		// Detect 3 in a line for 'O', and give a score of -100
		// Detect 2 in a line for 'O', and give a score of -10
		// Detect 1 in a line for 'O', (with 2 empty cells) and give a score of -1
		// Any other case, give a score of 0
		// Give +ve scores for 'X' according to the same rules above
	private int evaluateScore(MinimaxBoard board)	{
		int score = 0;
		
		// If the hasWon() function returns true for a player, this simply means that the 
		// player has got '3-in-a-line', anywhere in the board. And so, we return the a score of
		// 100 is X has won, or -100 if O has won.
		if (hasWon(board, Constants.X))	{
			return 100;
		}
		else if (hasWon(board, Constants.O))	{
			return -100;
		}
		
		// Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
	    score += evaluateLine(0, 0, 0, 1, 0, 2, board);  // row 0
	    score += evaluateLine(1, 0, 1, 1, 1, 2, board);  // row 1
	    score += evaluateLine(2, 0, 2, 1, 2, 2, board);  // row 2
	    score += evaluateLine(0, 0, 1, 0, 2, 0, board);  // col 0
	    score += evaluateLine(0, 1, 1, 1, 2, 1, board);  // col 1
	    score += evaluateLine(0, 2, 1, 2, 2, 2, board);  // col 2
	    score += evaluateLine(0, 0, 1, 1, 2, 2, board);  // diagonal
	    score += evaluateLine(0, 2, 1, 1, 2, 0, board);  // alternate diagonal
	    
	    return score;
	}
	
	
	private int evaluateLine(int row1, int col1, int row2, int col2, int row3, int col3, MinimaxBoard board) {
		int score = 0;
		
		// First cell
	    if (board.boardArray[row1][col1] == Constants.X) {
	    	score = 1;
	    } else if (board.boardArray[row1][col1] == Constants.O) {
	        score = -1;
	    }
	 
	    // Second cell
	    if (board.boardArray[row2][col2] == Constants.X) {
	    	if (score == 1) {   // cell1 is Constants.X
	    		score = 10;
	    	} else if (score == -1) {  // cell1 is Constants.O
	    		return 0;
	    	} else {  // cell1 is empty
	    		score = 1;
	    	}
	    } else if (board.boardArray[row2][col2] == Constants.O) {
	    	if (score == -1) { // cell1 is Constants.O
	    		score = -10;
        } else if (score == 1) { // cell1 is Constants.X
        	return 0;
        } else {  // cell1 is empty
            score = -1;
        }
      }
	    
	  // Third Cell
	  if (board.boardArray[row3][col3] == Constants.X) {
		  if (score > 0) {  // cell1 and/or cell2 is Constants.X
			  score *= 10;
	      } else if (score < 0) {  // cell1 and/or cell2 is Constants.O
	          return 0;
	      } else {  // cell1 and cell2 are empty
	    	  score = 1;
	      }
	  } else if (board.boardArray[row3][col3] == Constants.O) {
		  if (score < 0) {  // cell1 and/or cell2 is Constants.O
			  score *= 10;
	      } else if (score > 1) {  // cell1 and/or cell2 is Constants.X
	    	  return 0;
	      } else {  // cell1 and cell2 are empty
	         score = -1;
	      }
	  }
	  
	  return score;
	}

	// Main method included for debugging purposes
	public static void main(String args[])	{
		Board sampleBoard = new Board();
		sampleBoard.setState("XO----X--");
		MinimaxBoard minimaxBoard = new MinimaxBoard(sampleBoard);
		
		MinimaxGame game = new MinimaxGame();
		MinimaxResult result = game.play(minimaxBoard);
		
		String updatedBoardString = result.getUpdatedBoard().getBoard().getState();
		System.out.println(updatedBoardString);
	}
}
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
import java.util.List;

/**
 * Implements the minimax algorithm which is used by the
 * computer to calculate its next move against the player.
 * 
 * @author Prateek Mathur
 *
 */
public class Minimax {
	/**
	 * The 2D array that holds the board state for this instance.
	 */
	private char[][] boardArray;
	
	/**
	 * An int array that contains the bestMove for this state. Contains 2 elements- [bestRow, bestCol] 
	 */
	private int[] bestMove;
	
	/**
	 * The public constructor for Minimax.
	 * Accepts a string representation of the boardState, and uses it initialize the boardArray for this instance.
	 * 
	 * @param boardString
	 */
	public Minimax(String boardString)	{
		boardArray = convertBoardTo2D(boardString);
	}
	
	/**
	 * Executes the minimax algorithm on the boardArray. 
	 * This method should be called only after the boardArray is initialized, or it will throw an Exception.
	 * 
	 * @return A board object 
	 * @thorws Exception If the boardArray is not initialized.
	 */
	public Board play() throws Exception	{
		if (boardArray == null)	{
			throw new Exception("boardArray is not initialized");
		}
		
		Board updatedBoard = new Board();
		minimax(Constants.O);
		
		boardArray[bestMove[0]][bestMove[1]] = Constants.O;
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < boardArray.length; i++)	{
			builder.append(String.valueOf(boardArray[i]));
		}
		
		updatedBoard.setState(builder.toString());
		return updatedBoard;

	}
	
	/**
	 * Initializes the boardArray for this instance using the boardString.
	 * 
	 * @param boardString A string representing the current board State
	 * @return boardArray A 2D array generated from the board State
	 */
	private static char[][] convertBoardTo2D(String boardString)	{
		char[][] boardArray = new char[3][3];
	    char[] chars = boardString.toCharArray();
	    if (chars.length == 9) {
	      for (int i = 0; i < chars.length; i++) {
	        boardArray[i/3][i%3] = chars[i];
	      }
	    }
	    
	    return boardArray;
	}
	
	/**
	 * Minimax implementation
	 * This is a recursive implementation for Minimax.
	 *
	 * @param player A char representing whose turn it is
	 * @return bestScore The best score for the player who is playing
	 */
	private int minimax(char player)	{
		int bestScore;
		bestScore = (player == Constants.O) ? -1 : 1;
		
		int currentScore;
		
		List<Integer[]> nextMoves = getMoves();
		
		if (nextMoves.isEmpty() || hasWon(player))	{
			currentScore = evaluateScore();
		}
		
		for (Integer[] move : nextMoves)	{
			if (player == Constants.O)	{
				boardArray[move[0]][move[1]] = Constants.O;
				currentScore = minimax(Constants.X);
					
				if (currentScore > bestScore)	{
					bestScore = currentScore;
					bestMove = new int[] {move[0], move[1]};
				}
			}
			else if (player == Constants.X)	{
				boardArray[move[0]][move[1]] = Constants.X;
				currentScore = minimax(Constants.O);
					
				if (currentScore < bestScore)	{
					bestScore = currentScore;
					bestMove = new int[] {move[0], move[1]};
				}
			}
				
			boardArray[move[0]][move[1]] = Constants.DASH;
		}
		
		return bestScore;
	}
	
	/**
	 * Finds and returns a list of all possible next board states for a given state
	 * 
	 * @return allMoves An ArrayList of int arrays, containing the [row, col] of the next state
	 */
	private List<Integer[]> getMoves()	{
		List<Integer[]> allMoves = new ArrayList<Integer[]>();

		Integer [] validMove;
		
		for (int i = 0; i<3; i++)	{
			for (int j = 0; j<3; j++)	{
				if (boardArray[i][j] == Constants.DASH)	{
					validMove = new Integer[] {i, j};
					allMoves.add(validMove);
				}
			}
		}
		
		return allMoves;
	}
	
	/**
	 * Checks weather a player has won the game or not, by finding possible winning combinations in the board.
	 * 
	 * @param player Player whose victory needs to be checked
	 * @return True if the player has won, false otherwise
	 */
	private boolean hasWon(char player)	{
		boolean status = false;
		
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

	/**
	 * A deterministic evaluation function for Minimax.
	 * Returns +1 if computer wins.
	 * Returns -1 if player wins.
	 * Returns 0 if game is not over yet, or ends in a draw.
	 * 
	 * @return The appropriate score
	 */
	private int evaluateScore() {
		if (hasWon(Constants.O))	{
			return +1;
		}
		else if (hasWon(Constants.X))	{
			return -1;
		}
		else	{
			return 0;
		}
	}
	
	/* The main method, included for testing locally
	public static void main(String[] args)	{
		Minimax minimax = new Minimax("OO---XXX-");
		Board newBoard = null;
		try {
			newBoard = minimax.play();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("This is how minimax will play " + newBoard.getState());
	} */
	
}
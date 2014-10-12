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
	private char[][] boardArray;
	private int[] bestMove;
	
	public Minimax(String boardString)	{
		boardArray = convertBoardTo2D(boardString);
	}
	
	public Board play()	{
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
	
	private int minimax(char player)	{
		int bestScore;
		bestScore = (player == Constants.O) ? -1 : 1;
		
		int currentScore;
		
		// TODO 1
		// Code out the function to get all moves
		// And this time, let the values return as an array only
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
	
	private List<Integer[]> getMoves()	{
		List<Integer[]> allMoves = new ArrayList<Integer[]>();

		Integer validMove[];
		
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
	
	// The main method, included for testing locally
	public static void main(String[] args)	{
		Minimax minimax = new Minimax("OO---XXX-");
		Board newBoard = minimax.play();
		System.out.println("This is how minimax will play " + newBoard.getState());
	}
}
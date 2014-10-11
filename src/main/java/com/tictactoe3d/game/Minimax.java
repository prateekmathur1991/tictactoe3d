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
import java.util.Arrays;
import java.util.List;

/**
 * Implements the minimax algorithm which is used by the
 * computer to calculate its next move against the player.
 * 
 * @author Prateek Mathur
 *
 */
public class Minimax {

	// The idea is this-
	// The minimax class has its own boardArray, (remember not to make it static)
	// and we populate it with board from the client using the constructor.
	
	// Then, we do all our work on that boardArray only.
	
	private char[][] boardArray;
	private int[] bestMove;
	
	public Minimax(String boardString)	{
		boardArray = convertBoardTo2D(boardString);
	}

	public Board play(String boardString)	{
		char [][] boardArray = convertBoardTo2D(boardString);
		// boardArray = minimax(Constants.O);
		
		Board updatedBoard = new Board();
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
		
		if (nextMoves.isEmpty() || isGameOver())	{
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

		int validPositions = 0;
		Integer validMove[];
		
		for (int i = 0; i<3; i++)	{
			for (int j = 0; j<3; j++)	{
				if (boardArray[i][j] == Constants.DASH)	{
					validPositions++;
					validMove = new Integer[] {i, j};
					allMoves.add(validMove);
					validPositions++;
				}
			}
		}
		
		if (validPositions == 0)	{
			return null;
		}
		
		return allMoves;
	}
	
	/*private static int maxValue(char[][] boardArray) {
		int value;
		if (isGameOver(boardArray))	{
			return evaluateScore(boardArray);
		}
		else	{
			value = Integer.MIN_VALUE;
			Map<Integer, char[][]> nextMoves = getMoves(boardArray);
			for (Map.Entry<Integer, char[][]> mapEntry : nextMoves.entrySet())	{
				value = Math.max(value, minValue(mapEntry.getValue()));
			}
		}
		return value;
	}
	
	private static int minValue(char[][] boardArray) {
		int value;
		if (isGameOver(boardArray))	{
			return evaluateScore(boardArray);
		}
		else	{
			value = Integer.MAX_VALUE;
			Map<Integer, char[][]> nextMoves = getMoves(boardArray);
			for (Map.Entry<Integer, char[][]> mapEntry : nextMoves.entrySet())	{
				value = Math.min(value, maxValue(mapEntry.getValue()));
			}
		}
		return value;
	} */

	
		
	private boolean isGameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	private int evaluateScore() {
		// TODO Auto-generated method stub
		return 0;
	}
}
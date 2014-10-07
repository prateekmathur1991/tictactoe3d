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

import java.util.HashMap;
import java.util.Map;

/**
 * Implements the minimax algorithm which is used by the
 * computer to calculate its next move against the player.
 * 
 * @author Prateek Mathur
 *
 */
public class Minimax {
<<<<<<< HEAD
=======
	// We need to do this
	// 1. Create a static method named minimax, which accepts
	// the 2D array of board as argument, adds the best move in it,
	// and returns it.
<<<<<<< HEAD
>>>>>>> c13cdd3ad12446855f74c87135e77f1513c9013b
	public static Board play(String boardString)	{
		char [][] boardArray = convertBoardTo2D(boardString);
		boardArray = minimax(boardArray);
		
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
	
	private static char[][] minimax(char[][] boardArray)	{
		int max = maxValue(boardArray);
		Map<Integer, char[][]> moves = getMoves(boardArray);
		
		return moves.get(max);
	}
	
	private static int maxValue(char[][] boardArray) {
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
	}

	private static Map<Integer, char[][]> getMoves(char[][] boardArray)	{
		HashMap<Integer, char[][]> allMoves = new HashMap<Integer, char[][]>();
		
<<<<<<< HEAD
		int validPositions = 0;
		for (int i = 0; i<3; i++)	{
			for (int j = 0; j<3; j++)	{
				if (boardArray[i][j] == Constants.DASH)	{
					validPositions++;
				}
			}
		}
		
		if (validPositions > 0)	{
			
		}
		
		return allMoves;
	}
		
	private static boolean isGameOver(char[][] boardArray) {
		// TODO Auto-generated method stub
		return false;
	}

	private static int evaluateScore(char[][] boardArray) {
		// TODO Auto-generated method stub
		return 0;
=======
=======
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
>>>>>>> c4a4e405a32f1e693f67dda53a51c53a3704d9ef
		return null;
>>>>>>> c13cdd3ad12446855f74c87135e77f1513c9013b
	}

	
}

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
import java.util.List;
import java.util.Map;

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
	public static Board play(String boardString)	{
		char [][] boardArray = convertBoardTo2D(boardString);
		boardArray = minimax(boardArray, Constants.O);
		
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
	
	private static char[][] minimax(char[][] boardArray, char player)	{
		Map<Integer, char[][]> moves = getMoves();
		return null;
	}
	
	private static Map<Integer, char[][]> getMoves()	{
		HashMap<Integer, char[][]> allMoves = new HashMap<Integer, char[][]>();
		
		return null;
	}
}

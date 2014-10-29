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

import com.googlecode.objectify.annotation.Entity;

import com.tictactoe3d.game.Position;

import java.util.ArrayList;

/**
 * Represents a 2D Tic Tac Toe Board.
 * 
 * <p>The current state of the board is represented both as 
 * a 2D array, and a String. The String representation is retained to
 * support the code in render.js, so that it does not break.</p>
 * 
 * <p>In the future implementations of this game, the game can support saving the
 * current progress in the cloud, and continuing from where you left off (Hopefully!!). And that is why
 * the class is annotated with @Entity, which tells App Engine that Board instances can be
 * saved in and retrieved from the datastore.</p>
 * 
 * @author Prateek Mathur
 *
 */
@Entity
public class Board {
	public char[][] boardArray;
	public String state;
	
	public Board(String state)	{
		boardArray = convertBoardTo2D(state);
		this.state = state;
	}
	
	public Board(Board board)	{
		boardArray = convertBoardTo2D(board.getState());
		this.state = board.getState();
	}
	
	public Board(Board board, Position position, char player)	{
		boardArray = convertBoardTo2D(board.getState());
		boardArray[position.row][position.coloumn] = player;
		
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < boardArray.length; i++)	{
			builder.append(String.valueOf(boardArray[i]));
		}
		
		this.state = builder.toString();
	}
	
	public String getState()	{
		return state;
	}
	
	public char[][] convertBoardTo2D(String boardString)	{
	    char[][] boardArray = new char[3][3];
		char[] chars = boardString.toCharArray();
	    if (chars.length == 9) {
	      for (int i = 0; i < chars.length; i++) {
	        boardArray[i/3][i%3] = chars[i];
	      }
	    }
	    
	    return boardArray;
	}
	
	public ArrayList<Position> getAllPossibleMoves() {
		ArrayList<Position> allMoves = new ArrayList<Position>();
		
		for(int i = 0; i < 3; i++)	{
			for(int j = 0; j < 3; j++)	{
				if(boardArray[i][j] == '-')	{
                	allMoves.add(new Position(i, j));
                }
            }
		}
                 
		return allMoves;
	}
}

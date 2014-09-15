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

package com.tictactoe3d.spi;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;

import com.tictactoe3d.Constants;
import com.tictactoe3d.game.Board;

import java.util.Random;

/**
  * <p>Endpoint API for Tic Tac Toe.<p>
  * 
  * @author Prateek Mathur
  */
@Api(name="tictactoe",
	version="v1",
	scopes=Constants.EMAIL_SCOPE,
	clientIds={
		Constants.WEB_CLIENT_ID, 
		Constants.ANDROID_AUDIENCE, 
	},
	description="API for 2D Tic Tac Toe"
)
public class TicTacToeApi {
	private static final char X = 'X';
	private static final char O = 'O';
	private static final char DASH = '-';
	
	/**
	 * Computes the next move of the computer based on the current state of the board.
	 * 
	 * @param A Board object representing the current board state
	 * @return A Board object with the updated Board state
	 */
	@ApiMethod(name="compute2DMove", httpMethod=HttpMethod.POST)
	public Board compute2DMove(Board board)	{
		// Convert the String representation of the board in a 2D array
		char [][] boardArray = convertBoardTo2D(board.getState());
		
		// Count the no. of free blocks
		 int freeBlocks = 0;
		 for (int i = 0; i < boardArray.length; i++) {
			 for (int j = 0; j < boardArray[i].length; j++) {
		        if (boardArray[i][j] != X && boardArray[i][j] != O) {
		          freeBlocks++;
		        }
		     }
		 }
		 
		// Given the array of blocks and no. of free blocks, we can
		// find the next optimal move
		boardArray = addMove(boardArray, freeBlocks);
		   
		// After adding the computer's move, we need to build a
		// string of the board, and return it
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < boardArray.length; i++)	{
			builder.append(String.valueOf(boardArray[i]));
		}
		
		Board updatedBoard = new Board();
		updatedBoard.setState(builder.toString());
		
		return updatedBoard;
	}
	
	private char[][] convertBoardTo2D(String boardString)	{
		char[][] boardArray = new char[3][3];
	    char[] chars = boardString.toCharArray();
	    if (chars.length == 9) {
	      for (int i = 0; i < chars.length; i++) {
	        boardArray[i/3][i%3] = chars[i];
	      }
	    }
	    
	    return boardArray;
	}
	
	private char[][] addMove(char[][] boardArray, int freeBlocks)	{
		int index = new Random().nextInt(freeBlocks) + 1;
		for (int i = 0; i < boardArray.length; i++)	{
	      for (int j = 0; j < boardArray.length; j++)	{
	    	  if (boardArray[i][j] == DASH) {
	    		  if (freeBlocks == index) {
	    			  boardArray[i][j] = O;
	    			  return boardArray;
	    		  } 
	    		  else {
	    			  freeBlocks--;
	    		  }
	    	  }
	      }
	   }
	   return boardArray;
	}
	
}


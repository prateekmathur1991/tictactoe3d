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

/**
 * Represents a 2D Tic Tac Toe Board.
 * 
 * <p>The current state of the board is represented as 
 * a 1D string. For example, ----X---- represents a board with an X in the center,
 * and rest all positions empty.</p>
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
public class Board2D {
	public String state;
	
	public String getState()	{
		return state;
	}
	
	public void setState(String state)	{
		this.state=state;
	}
}

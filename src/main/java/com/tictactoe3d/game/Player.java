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

import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Represents a player whose plays the game.
 * 
 * The total no. of games played by the player, along with
 * the no. of won games, will be saved in the datastore. This data
 * is used to calculate the performance of the player.
 */
@Entity
public class Player {
	
	@Id
	private String userId;
	
	private String name;
	private int gamesPlayed;
	private int wonGames;
	
	private float performance;
	
	/**
	 * The constructor for Player class.
	 * Creates an initial player object for a user that has just started playing the game.
	 * 
	 * @param name Name of the Player as supplied by the player himself
	 * @param user A user object representing the signed-in user
	 * 
	 * @throws UnauthorizedException If the user is not signed-in
	 */
	public Player(String name, final User user) throws UnauthorizedException	{
		
		if (user==null)	{
			throw new UnauthorizedException("Sign-in required");
		}
		
		this.name=name;
		userId=user.getUserId();
		
		gamesPlayed=0;
		wonGames=0;
		performance=0;
	}
	
	/**
	 * Updates the total games played and total games won, when the player finishes a game
	 * 
	 * @param outcome A boolean variable representing the outcome of a game. True if the player has won, false otherwise.
	 */
	public void updatePlayer(boolean outcome)	{
		gamesPlayed++;
		
		// Update the wonGames variable only if the player has won the game
		if (outcome)	{
			wonGames++;
		}
		
		// Change the performance accordingly
		performance=(wonGames/gamesPlayed)*100;
		
	}
	
	// Making the default constructor private
	private Player() { }
}

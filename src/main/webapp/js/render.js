
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

/**
 * Provides methods for UI interaction and communicating with the tictactoe2D API.
 * 
 * @author Prateek Mathur
 */

/**
 * Status for an unfinished game
 */
var UNFINISHED = 0;

/**
 * Status for victory 
 */
var WON = 1;

/**
 * Status for loss
 */
var LOST = 2;

/**
 * Status for tie
 */
var TIE = 3;

/**
 * <img> tag string for setting O
 */
var stringX = "<img src=img/X.png></img>";

/**
 * <img> tag string for setting O
 */
var stringO = "<img src=img/O.png></img>";

/**
 * Client ID of the application (from the APIs Console).
 * @type {string}
 */
var CLIENT_ID = '57795119450-mnvhk8v12sltc03s667fsven0pkvcb28.apps.googleusercontent.com';

/**
 * Scopes used by the application.
 * @type {string}
 */
var SCOPES = 'https://www.googleapis.com/auth/userinfo.email ' + 'https://www.googleapis.com/auth/plus.login';

/**
 * This function is called after the Google Client JavaScript API loads.
 * It sets the API key, post which it loads our tictactoe2D API.
 */
function init()	{
	gapi.client.setApiKey('AIzaSyAKjDAYSatb3cS_DxlKpjc5K6yuvfGN4Fs');
	gapi.client.load('tictactoe', 'v1', null, '//' + window.location.host + '/_ah/api');	
}

$(document).ready(function() {
	
	// Recommended code to load the Google+ script
	(function() {
	 var po = document.createElement('script'); 
	 po.type = 'text/javascript'; 
	 po.async = true;
	 po.src = 'https://apis.google.com/js/client:plusone.js?onload=renderButton';
	 var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
	})();
	
	associateClickHandler();
});

/**
 * Renders the Google+ Sign-In button with given parameters
 */
function renderButton()	{
	gapi.signin.render('signIn', {
	    'callback': signInCallback,
	    'clientid': CLIENT_ID,
	    'cookiepolicy': 'single_host_origin',
	    'requestvisibleactions': 'http://schemas.google.com/AddActivity',
	    'scope': SCOPES
	  });
}

/**
 * Handles sign in response of the user
 */
function signInCallback(authResult)	{
	// The authResult object will contain an access token and id_token if
	// the user has signed in
	if (authResult.access_token && authResult.id_token)	{	
		// Hide the warning and sign-in button, and show the game board and restart button upon successful sign-in
		document.getElementById('signInSection').classList.add('hidden');
		
		document.getElementById('board').classList.remove('hidden');
		document.getElementById('restart').classList.remove('hidden');
	}
	else if (authResult.error)	{
		console.log("Error occoured "+authResult.error);
	}
	
	if (authResult == null)	{
		consolelog("The user did not sign in");
	}

}

/**
* Associate click event handler with all squares
*/
function associateClickHandler()	{
	var squares = document.querySelectorAll('td');
	for (var i=0; i<squares.length; i++)	{
		var square = squares[i];
		square.addEventListener('click', clickHandler);
	}
}

/**
* Handler for the click event of all squares
* 
* @param e A MouseClick event generated by the square that was clicked upon
*/
var clickHandler = function(e)	{
	// Set the 'X' character on the square which is clicked
	var square = e.target;
	square.innerHTML = stringX;
	
	// Make sure that the square just clicked upon will not be clicked upon again
	square.removeEventListener('click', clickHandler);
	
	// Get the string of the board
	var boardString = getBoardString();
	
	// Check for victory
	var gameStatus = checkForVictory(boardString);
	
	// Execute and construct a request only if the game has to continue
	if (gameStatus == UNFINISHED)	{
		// Construct the JSON-RPC request
		var request = gapi.client.tictactoe.compute2DMove({'state': boardString});
		
		// Execute the request, and update the board with the new board state
		request.execute(function (response)	{
			updateBoard(response.state);
			
			// Check the status of the game, and take appropriate action if game cannot continue
			gameStatus = checkForVictory(response.state);
			if (gameStatus != UNFINISHED)	{
				handleFinish(gameStatus);
			}
		});
	}
	else	{
		handleFinish(gameStatus);
	}
};

/**
 * Get a string representation of the board
 */
function getBoardString()	{
	var board = [];
	
	var squares = document.querySelectorAll('td');
	for (var i=0; i<squares.length; i++)	{
		var string = squares[i].innerHTML;
		
		// If there is an X in the square, the innerHTML string will contain the <img>
		// tag with the character 'X' somewhere in it
		if (string.indexOf('X') != -1)	{
			board.push('X');
		}
		else if (string.indexOf('O') != -1)	{
			board.push('O');
		}
		else
			board.push('-');
	}
	
	return board.join('');
}

/**
 * Fills the board with the updated board state
 * 
 * @param boardString String representation of current board
 */
function updateBoard(boardString)	{
	var squares = document.querySelectorAll('td');
	for (var i=0; i<squares.length; i++)	{
		var square = squares[i];
		
		// Wherever there is an 'O' in the string, display the image of O
		if (boardString.charAt(i) == 'O')	{
			square.innerHTML = stringO;
			square.removeEventListener('click', clickHandler);
		}
	}
}

/**
 * Checks for a victory condition
 * 
 * @param boardString String representation of current board
 */
function checkForVictory(boardString)	{
	var status = UNFINISHED;

	  // Checks rows and columns.
	  for (var i = 0; i < 3; i++) {
	    var rowString = getStringsAtPositions(boardString, i*3, (i*3)+1, (i*3)+2);
	    status |= checkSectionVictory(rowString);

	    var colString = getStringsAtPositions(boardString, i, i+3, i+6);
	    status |= checkSectionVictory(colString);
	  }

	  // Check top-left to bottom-right.
	  var diagonal = getStringsAtPositions(boardString, 0, 4, 8);
	  status |= checkSectionVictory(diagonal);

	  // Check top-right to bottom-left.
	  diagonal = getStringsAtPositions(boardString, 2, 4, 6);
	  status |= checkSectionVictory(diagonal);

	  if (status == UNFINISHED) {
	    if (boardString.indexOf('-') == -1) {
	      return TIE;
	    }
	  }

	  return status;
}

/**
 * Checks whether a set of three squares are identical.
 * 
 * @param {string} section Set of three squares to check.
 * @return {number} Status code for the victory state.
 */
function checkSectionVictory(section) {
	  var a = section.charAt(0);
	  var b = section.charAt(1);
	  var c = section.charAt(2);
	  if (a == b && a == c) {
	    if (a == 'X') {
	      return WON;
	    } else if (a == 'O') {
	      return LOST;
	    }
	  }
	  return UNFINISHED;
}

/**
 * Gets the values of the board at the given positions.
 * 
 * @param boardString Current state of the board.
 * @param first First element to retrieve.
 * @param second Second element to retrieve.
 * @param third Third element to retrieve.
 */
function getStringsAtPositions(boardString, first, second, third) {
	  return [boardString.charAt(first),
	          boardString.charAt(second),
	          boardString.charAt(third)].join('');
}

/**
 * Handles end of the game.
 * 
 * @param gameStatus Status of the game
 */
function handleFinish(gameStatus)	{
	if (gameStatus == WON)	{
		document.getElementById('gameResult').innerHTML = 'YOU WON!!!';
	}
	else if (gameStatus == LOST)	{
		document.getElementById('gameResult').innerHTML = 'You Lost :(';
	}
	else	{
		document.getElementById('gameResult').innerHTML = 'Game Tied!';
	}
}

/**
 * Restarts the game.
 * It simply sets all the squares to contain '-' again.
 */
function restartGame()	{
	if (confirm("Are you sure you want to restart the game?") == true)	{
		var squares = document.querySelectorAll('td');
		for (var i=0; i<squares.length; i++)	{
			var square = squares[i];
			square.innerHTML = '-';
		}
	}
}

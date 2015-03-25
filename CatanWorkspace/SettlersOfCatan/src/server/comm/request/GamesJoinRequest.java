package server.comm.request;

import server.comm.response.GameResponse;
import server.comm.response.IResponse;
import shared.comm.ServerException;

/**
 * The Class GamesJoinRequest, Adds the player to the specified game and sets their catan.game cookie
 * 
 * @pre 
 * 	The user has previously logged in to the server (i.e., they have a valid catan.user HTTP cookie).
 *  The player may join the game because
 *  They are already in the game, OR
 *  There is space in the game to add a new player
 *  The specified game ID is valid
 *  The specified color is valid (red, green, blue, yellow, puce, brown, white, purple, orange)
 *  
 * @post
 *  The server returns an HTTP 200 success response with �Success� in the body.
 *  The player is in the game with the specified color (i.e. calls to /games/list method will show the player in the game with the chosen color).
 *  The server response includes the �Set�cookie� response header setting the catan.game
 */
public class GamesJoinRequest extends AbstractRequest<GameResponse> {

	@Override
	public GameResponse getResponse() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

}

package server.comm.request;

import server.comm.response.IResponse;
import shared.comm.ServerException;

/**
 * The Class GamesGetCommandsRequest, Returns a list of commands that have been executed in the current game.
 * @pre 
 * 	The caller has previously logged in to the server and joined a game
 * 
 * 
 * @post
 * 	The server returns an HTTP 200 success response
 *  The body contains a JSON array of commands that have been executed in the game.
 *  This command array is suitable for passing back to the /game/command [POST] method to restore the state of the game later (after calling /game/reset to revert the game to its initial state).
 * 	If it fails, The server returns an HTTP 400 error response, and the body contains an error message.
 * 	
 */
public class GameGetCommandsRequest extends Request {

	@Override
	public IResponse getResponse() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

}

package server.comm.request;

import server.comm.response.IResponse;
import shared.comm.ServerException;

/**
 * The Class GamesExecuteCommandsRequest, Executes the specified command list in the current game.
 * @pre 
 * 	The caller has previously logged in to the server and joined a game
 * 
 * @post
 * 	The passed-in command list has been applied to the game.
 * 	The server returns an HTTP 200 success response
 * 	The body contains the game's updated client model JSON
 */
public class GamePostCommandsRequest extends Request {

	@Override
	public IResponse getResponse() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

}

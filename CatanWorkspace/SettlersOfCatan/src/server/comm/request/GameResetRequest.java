package server.comm.request;

import server.comm.response.CatanModelResponse;
import shared.comm.ServerException;

/**
 * The Class GamesResetRequest, Clears out the command history of the current game.
 * @pre 
 * 	The caller has previously logged in to the server and joined a game
 * 
 * 
 * @post
 * 	The game�s command history has been cleared out
 *  The game�s players have NOT been cleared out
 *  The server returns an HTTP 200 success response.
 * 	The body contains the game�s updated client model JSON
 * 	
 */
public class GameResetRequest extends AbstractRequest<CatanModelResponse> {

	@Override
	public CatanModelResponse getResponse() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

}

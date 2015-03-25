package server.comm.request;

import server.comm.response.CatanModelResponse;
import shared.comm.ServerException;

/**
 * The Class GamesModelVersionRequest, Returns the current state of the game in JSON format.
 * @pre 
 * 	The caller has previously logged in to the server and joined a game
 * 	If specified, the version number is included as the �version� query parameter in the request URL, and its value is a valid integer
 * 
 * @post
 * 	The server returns an HTTP 200 success response.
 * 	The response body contains JSON data
 * 	
 */
public class GameModelRequest extends AbstractRequest<CatanModelResponse> {

	@Override
	public CatanModelResponse getResponse() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

}

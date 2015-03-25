package server.comm.request;

import server.comm.response.IResponse;
import shared.comm.ServerException;

/**
 * The Class GamesListAIRequest, Returns a list of supported AI player types
 * @pre 
 * 	None
 * 
 * 
 * @post
 * 	The server returns an HTTP 200 success response.
 *  The body contains a JSON string array enumerating the different types of AI players. These are the values that may be passed to the /game/addAI method.
 * 	
 */
public class GamesAddAIRequest extends AbstractRequest {

	@Override
	public IResponse getResponse() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

}

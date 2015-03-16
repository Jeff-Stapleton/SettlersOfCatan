package server.comm.request;

import server.comm.response.IResponse;
import shared.comm.ServerException;

/**
 * The Class GamesListRequest, Returns information about all of the current games on the server.
 * 
 * @pre
 * 	None
 * 
 * @post
 * 	The server returns an HTTP 200 success response.
 *  The body contains a JSON array containing a list of objects that contain information about the server’s games
 * 	
 */
public class GamesListRequest extends Request {

	@Override
	public IResponse getResponse() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

}

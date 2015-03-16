package server.comm.request;

import server.comm.response.IResponse;
import shared.comm.ServerException;

/**
 * The Class MovesFinishTurnRequest.
 * 
 * @pre
 * 	None
 * 
 * @post
 * 	The cards in your new dev card hand have been transferred to your old dev card hand
 * 	It is the next player’s turn
 */
public class MovesFinishTurnRequest extends Request {

	@Override
	public IResponse getResponse() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

}

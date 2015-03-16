package server.comm.request;

import server.comm.response.IResponse;
import shared.comm.ServerException;

/**
 * The Class MovesRollNumberRequest.
 * 
 * @pre
 * 	It is your turn
 * 	The client model’s status is ‘Rolling’
 * 
 * @post
 * 	The client model’s status is now in ‘Discarding’ or ‘Robbing’ or ‘Playing’
 * 	
 * 
 */
public class MovesRollNumberRequest extends Request {

	@Override
	public IResponse getResponse() throws ServerException {
		// TODO Auto-generated method stub
		return null;
	}

}
